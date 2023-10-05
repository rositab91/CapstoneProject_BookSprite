package rositabongiovanni.booksprite.googlebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleBookVolumeInfoRepository extends JpaRepository<GoogleBookVolumeInfo, String> {
}
