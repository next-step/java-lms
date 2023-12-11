package nextstep.courses.domain.coverImage;

import java.util.ArrayList;
import java.util.List;

public class CoverImages {

    private List<CoverImage> coverImages = new ArrayList<>();

    public CoverImages() {
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public List<CoverImage> getCoverImages() {
        return coverImages;
    }
}
