package rositabongiovanni.booksprite.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rositabongiovanni.booksprite.config.JwtService;
import rositabongiovanni.booksprite.token.Token;
import rositabongiovanni.booksprite.token.TokenRepository;
import rositabongiovanni.booksprite.token.TokenType;
import rositabongiovanni.booksprite.user.User;
import rositabongiovanni.booksprite.user.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class AuthenticationService {

  @Autowired
  private  UserRepository repository;

  @Autowired
  private  TokenRepository tokenRepository;

  @Autowired
  private  PasswordEncoder passwordEncoder;

  @Autowired
  private  JwtService jwtService;

  @Autowired
  private  AuthenticationManager authenticationManager;


  /**
   * Registers a new user and returns an authentication response.
   *
   * @param request Registration request containing user information.
   * @return Authentication response containing access and refresh tokens.
   */
  public AuthenticationResponse register(RegisterRequest request) {
    User user = new User();
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    User savedUser = repository.save(user);
    String jwtToken = jwtService.generateToken(savedUser);
    String refreshToken = jwtService.generateRefreshToken(savedUser);
    saveUserToken(savedUser, jwtToken);
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setAccessToken(jwtToken);
    authenticationResponse.setRefreshToken(refreshToken);
    return authenticationResponse;
  }

  /**
   * Authenticates a user and returns an authentication response.
   *
   * @param request Authentication request containing user credentials.
   * @return Authentication response containing access and refresh tokens.
   */
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    User user = repository.findByEmail(request.getEmail()).orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    AuthenticationResponse response = new AuthenticationResponse();
    response.setAccessToken(jwtToken);
    response.setRefreshToken(refreshToken);
    return response;
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = new Token();
    token.setUser(user);
    token.setToken(jwtToken);
    token.setTokenType(TokenType.BEARER);
    token.setExpired(false);
    token.setRevoked(false);
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    Iterable<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (!validUserTokens.iterator().hasNext()) {
      return;
    }
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  /**
   * Refreshes the access token using a valid refresh token.
   *
   * @param request  HTTP request containing the refresh token in the Authorization header.
   * @param response HTTP response where the new access token will be written.
   * @throws IOException if an error occurs while writing the response.
   */
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    String refreshToken;
    String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      User user = repository.findByEmail(userEmail).orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        String accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
