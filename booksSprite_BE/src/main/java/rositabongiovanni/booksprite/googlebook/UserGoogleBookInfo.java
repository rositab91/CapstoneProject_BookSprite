package rositabongiovanni.booksprite.googlebook;

import jakarta.persistence.*;
import lombok.Getter;
import rositabongiovanni.booksprite.user.User;

@Table(name = "userGoogleBookInfo")
@Entity
public class UserGoogleBookInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private GoogleBookVolumeInfo googleBookVolumeInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String rating;

    public void setGoogleBookVolumeInfo(GoogleBookVolumeInfo googleBookVolumeInfo) {
        this.googleBookVolumeInfo = googleBookVolumeInfo;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoogleBookVolumeInfo getGoogleBookVolumeInfo() {
        return googleBookVolumeInfo;
    }

    public User getUser() {
        return user;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
