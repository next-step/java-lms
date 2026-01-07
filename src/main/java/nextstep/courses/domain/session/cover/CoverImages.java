package nextstep.courses.domain.session.cover;

import java.util.ArrayList;
import java.util.List;

public class CoverImages {

    private final List<CoverImage> coverImages;

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public List<CoverImage> getValues() {
        return new ArrayList<>(coverImages);
    }

    public int size() {
        return coverImages.size();
    }


}
