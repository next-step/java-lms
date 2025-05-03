package nextstep.session.domain.image;

import java.util.ArrayList;
import java.util.List;

public class CoverImages {
    private List<CoverImage> coverImages;

    public CoverImages() {
        this.coverImages = new ArrayList<>();
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = (coverImages == null) ? new ArrayList<>() : coverImages;
    }

    public int size() {
        return coverImages.size();
    }

    public CoverImage get(int index) {
        return coverImages.get(index);
    }

    public void add(CoverImage coverImage) {
        coverImages.add(coverImage);
    }

    public List<CoverImage> images() {
        return coverImages;
    }

    public boolean isEmpty() {
        return coverImages.isEmpty();
    }
}
