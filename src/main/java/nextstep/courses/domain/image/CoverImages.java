package nextstep.courses.domain.image;

import java.util.ArrayList;
import java.util.List;

public class CoverImages {

    private List<CoverImage> coverImages;

    public CoverImages() {
        this.coverImages = new ArrayList<>();
    }

    public void add(CoverImage coverImage) {
        coverImages.add(coverImage);
    }

    public int size() {
        return coverImages.size();
    }

    public List<CoverImage> getCoverImages() {
        return coverImages;
    }

}
