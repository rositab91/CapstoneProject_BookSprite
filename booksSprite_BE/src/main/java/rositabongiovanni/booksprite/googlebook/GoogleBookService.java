package rositabongiovanni.booksprite.googlebook;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import rositabongiovanni.booksprite.user.User;
import rositabongiovanni.booksprite.user.UserGoogleBookInfoRepository;
import rositabongiovanni.booksprite.user.UserRepository;
import rositabongiovanni.booksprite.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class GoogleBookService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGoogleBookInfoRepository userbookGoogleInfoRepository;

    @Autowired
    GoogleBookVolumeInfoRepository googleBookVolumeInfoRepository;

    public UserGoogleBookInfo saveBook(GoogleBookVolumeInfo googleBookVolumeInfo) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (user != null) {
            GoogleBookVolumeInfo googleBookVolumeInfo1 = googleBookVolumeInfoRepository.save(googleBookVolumeInfo);
            UserGoogleBookInfo userGoogleBookInfo = new UserGoogleBookInfo();
            userGoogleBookInfo.setUser(user);
            userGoogleBookInfo.setGoogleBookVolumeInfo(googleBookVolumeInfo1);
            userbookGoogleInfoRepository.save(userGoogleBookInfo);
            return userGoogleBookInfo;
        } else {
            throw new EntityNotFoundException("User not found ");
        }
    }

    public List<UserGoogleBookInfo> getBooksForCurrentUser(Integer userId) {
        return userbookGoogleInfoRepository.findByUserId(userId);
    }

    public boolean deleteCurrentUserBookByBookId(Integer userId, Integer bookId) {
        List<UserGoogleBookInfo> userBooks = userbookGoogleInfoRepository.findByUserId(userId);
        for (UserGoogleBookInfo userBook : userBooks) {
            if (userBook.getGoogleBookVolumeInfo().getId().equals(bookId)) {
                userbookGoogleInfoRepository.delete(userBook);
                return true;
            }
        }
        return false;
    }

    public boolean saveBookRatingForCurrentUser(Integer userId, Integer bookId, String rating) {
        // Retrieve the user's book by bookId
        List<UserGoogleBookInfo> userBooks = userbookGoogleInfoRepository.findByUserId(userId);
        for (UserGoogleBookInfo userBook : userBooks) {
            if (userBook.getGoogleBookVolumeInfo().getId().equals(bookId)) {
                userBook.setRating(rating);
                userbookGoogleInfoRepository.save(userBook);
                return true;
            }
        }
        return false;  // Book not found or couldn't save the rating
    }

}
