package nextstep.courses.domain.session;

import nextstep.courses.domain.CoverImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CoverImages {
    private final List<CoverImage> coverImages;

    public CoverImages(CoverImage... coverImages) {
        this(List.of(coverImages));
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = new ArrayList<>(coverImages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImages that = (CoverImages) o;
        return Objects.equals(coverImages, that.coverImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coverImages);
    }

    public List<CoverImage> getCoverImages() {
        return Collections.unmodifiableList(coverImages);
    }

    public void add(CoverImage coverImage) {
        coverImages.add(coverImage);
    }

    public int size() {
        return coverImages.size();
    }
}
