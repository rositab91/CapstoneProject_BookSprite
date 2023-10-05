package rositabongiovanni.booksprite.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

  // Fields
  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("refresh_token")
  private String refreshToken;

  private String firstname;

  // Constructors
  public AuthenticationResponse() {
    // Default constructor
  }

  public AuthenticationResponse(String accessToken, String refreshToken, String firstname) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.firstname = firstname;
  }

  // Getter for accessToken
  public String getAccessToken() {
    return accessToken;
  }

  // Setter for accessToken
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  // Getter for refreshToken
  public String getRefreshToken() {
    return refreshToken;
  }

  // Setter for refreshToken
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  // Getter for firstname
  public String getFirstname() {
    return firstname;
  }

  // Setter for firstname
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  @Override
  public String toString() {
    return "AuthenticationResponse{" +
            "accessToken='" + accessToken + '\'' +
            ", refreshToken='" + refreshToken + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }


}
