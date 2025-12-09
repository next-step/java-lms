package nextstep.courses.domain.image;

import java.util.ArrayList;
import java.util.List;

public class CoverImages {

    private List<CoverImage> coverImages;

    public CoverImages() {
        this.coverImages = new ArrayList<>();
    }

    public CoverImages(CoverImage... coverImage) {
        this.coverImages = List.of(coverImage);
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
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
