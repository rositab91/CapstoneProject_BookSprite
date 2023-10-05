package rositabongiovanni.booksprite.auth;

public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;

  // Constructor
  public RegisterRequest(String firstname, String lastname, String email, String password) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
  }

  // Getter for firstname
  public String getFirstname() {
    return firstname;
  }

  // Setter for firstname
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  // Getter for lastname
  public String getLastname() {
    return lastname;
  }

  // Setter for lastname
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  // Getter for email
  public String getEmail() {
    return email;
  }

  // Setter for email
  public void setEmail(String email) {
    this.email = email;
  }

  // Getter for password
  public String getPassword() {
    return password;
  }

  // Setter for password
  public void setPassword(String password) {
    this.password = password;
  }
}
