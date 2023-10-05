package rositabongiovanni.booksprite.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Find a user by their email address.
   *
   * @param email The email address to search for.
   * @return An optional containing the user if found, or empty if not found.
   */
  Optional<User> findByEmail(String email);

}
