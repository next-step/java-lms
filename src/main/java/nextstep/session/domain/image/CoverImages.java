package nextstep.session.domain.image;

import java.util.List;

public class CoverImages {
    private List<CoverImage> coverImages;

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
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
}
