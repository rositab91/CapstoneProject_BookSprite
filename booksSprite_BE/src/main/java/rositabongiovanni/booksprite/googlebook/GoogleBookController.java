package rositabongiovanni.booksprite.googlebook;

import lombok.extern.slf4j.Slf4j;
import rositabongiovanni.booksprite.user.User;
import rositabongiovanni.booksprite.user.UserGoogleBookInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/googlebooks/")
@Slf4j
public class GoogleBookController {

    private final String googleBooksApiUrl = "https://www.googleapis.com/books/v1/";

    @Autowired
    private GoogleBookService googleBookService;

    @Autowired
    UserGoogleBookInfoRepository userbookGoogleInfoRepository;

    @Autowired
    GoogleBookVolumeInfoRepository googleBookVolumeInfoRepository;
    /**
     * Function to fetch all google books using the google book api's
     * The response from the google book api is directly mapped to
     * the Java Object GoogleBooks
     *
     * No Jackson or other Json libraries are needed to map the response
     * to the java object
     * @param name
     * @return List of google books
     */
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<GoogleBook> getBook(@PathVariable("name") String name) {

        name = name.replaceAll(" ", "%20");
        String url = googleBooksApiUrl + "/volumes?q=" + name + "&maxResults=40";

        // Get the rest template
        RestTemplate restTemplate = new RestTemplate();

        // Call the api to get books
        ResponseEntity<GoogleBook> responseEntity = restTemplate
                .getForEntity(url, GoogleBook.class );

        GoogleBook book = new GoogleBook();
        book = responseEntity.getBody();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/")
    public GoogleBookVolumeInfo saveBookForUser(@RequestBody GoogleBookVolumeInfo book) {
            UserGoogleBookInfo savedBook = googleBookService.saveBook(book);
            GoogleBookVolumeInfo book_ = savedBook.getGoogleBookVolumeInfo();
            return book_;
    }

    @GetMapping("/user")
    public List<GoogleBookVolumeInfo> getBooksForCurrentUser() {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<UserGoogleBookInfo> userBooks = googleBookService.getBooksForCurrentUser(user.getId());
            List<GoogleBookVolumeInfo> books = userBooks.stream().map(UserGoogleBookInfo::getGoogleBookVolumeInfo).toList();
        return books;
    }

    @DeleteMapping("/{bookId}")
    public Boolean deleteBookForCurrentUser(@PathVariable("bookId") Integer bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean bookDeleted = googleBookService.deleteCurrentUserBookByBookId(user.getId(), bookId);
        return bookDeleted;
    }

    @PostMapping("/rating/{bookId}")
    public String saveBookRatingForCurrentUser(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String rating
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean ratingSaved = googleBookService.saveBookRatingForCurrentUser(user.getId(), bookId, rating);
        if (ratingSaved)
            return rating;
        return null;
    }

}
