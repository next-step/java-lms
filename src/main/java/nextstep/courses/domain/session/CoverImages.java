package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoverImages {
    private final List<CoverImage> coverImages;

    public CoverImages() {
        this(new ArrayList<>());
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public List<CoverImage> getCoverImages() {
        return Collections.unmodifiableList(coverImages);
    }
}
