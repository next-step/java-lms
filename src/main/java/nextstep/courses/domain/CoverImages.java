package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class CoverImages {

    private final List<CoverImage> coverImages;

    public CoverImages() {
        this.coverImages = new ArrayList<>();
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }
}
