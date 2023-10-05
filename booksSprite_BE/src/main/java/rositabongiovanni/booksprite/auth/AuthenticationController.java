package rositabongiovanni.booksprite.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import rositabongiovanni.booksprite.user.User;
import rositabongiovanni.booksprite.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  @Autowired
  private  AuthenticationService service; // Use constructor injection instead of @Autowired

  @Autowired
  private  UserRepository userRepository; // Use constructor injection instead of @Autowired


  /**
   * Register a new user.
   *
   * @param request The registration request body.
   * @return ResponseEntity containing the registration response.
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  /**
   * Authenticate a user.
   *
   * @param request The authentication request body.
   * @return ResponseEntity containing the authentication response.
   */
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    AuthenticationResponse response = service.authenticate(request);
    Optional<User> user = userRepository.findByEmail(request.getEmail());
    response.setFirstname(user.get().getFirstname());
    return ResponseEntity.ok(response);
  }

  /**
   * Refresh the authentication token.
   *
   * @param request  The HTTP servlet request.
   * @param response The HTTP servlet response.
   * @throws IOException If there's an IO exception.
   */
  @PostMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
}
