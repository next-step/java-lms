package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoverImages {
    private List<CoverImage> coverImages;

    public CoverImages() {
        this(new ArrayList<>());
    }

    public CoverImages(CoverImage... coverImages) {
        this(Arrays.asList(coverImages));
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public List<CoverImage> getCoverImages() {
        return new ArrayList<>(coverImages);
    }

    public void add(CoverImage coverImage) {
        if (coverImage == null) {
            return;
        }

        this.coverImages.add(coverImage);
    }

    public void addAll(List<CoverImage> coverImages) {
        if (coverImages == null || coverImages.isEmpty()) {
            return;
        }

        this.coverImages.addAll(coverImages);
    }

    @Override
    public String toString() {
        return "CoverImages{" +
                "coverImages=" + coverImages +
                '}';
    }
}
