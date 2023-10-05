package rositabongiovanni.booksprite.googlebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GoogleBookItem {
    GoogleBookVolumeInfoFront volumeInfo;

    public GoogleBookVolumeInfoFront getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GoogleBookVolumeInfoFront volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
