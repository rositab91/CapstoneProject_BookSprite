package rositabongiovanni.booksprite.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rositabongiovanni.booksprite.googlebook.UserGoogleBookInfo;

import java.util.List;

/**
 * This repository interface is responsible for managing user-specific Google Book information.
 */
@Repository
public interface UserGoogleBookInfoRepository extends JpaRepository<UserGoogleBookInfo, Integer> {

    /**
     * Retrieve a list of UserGoogleBookInfo objects by a specific user's ID.
     *
     * @param userId The ID of the user for whom to retrieve the book information.
     * @return A list of UserGoogleBookInfo objects associated with the specified user.
     */
    List<UserGoogleBookInfo> findByUserId(Integer userId);
}
