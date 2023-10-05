package rositabongiovanni.booksprite.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import rositabongiovanni.booksprite.googlebook.UserGoogleBookInfo;
import rositabongiovanni.booksprite.token.Token;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "_user") // Use "user" instead of "_user" for table name
public class User implements UserDetails {

  // Getter and Setter methods for id, firstname, lastname, email, password
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.IDENTITY for auto-increment
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @OneToMany(mappedBy = "user")
  private List<UserGoogleBookInfo> myBooks;

  public User() {
    // Default constructor
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Token> getTokens() {
    return tokens;
  }

  public void setTokens(List<Token> tokens) {
    this.tokens = tokens;
  }

  public List<UserGoogleBookInfo> getMyBooks() {
    return myBooks;
  }

  public void setMyBooks(List<UserGoogleBookInfo> myBooks) {
    this.myBooks = myBooks;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null; // You can implement this to return user roles/permissions
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // You can implement logic for account expiration
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // You can implement logic for account locking
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // You can implement logic for credentials expiration
  }

  @Override
  public boolean isEnabled() {
    return true; // You can implement logic for enabling/disabling accounts
  }


}
