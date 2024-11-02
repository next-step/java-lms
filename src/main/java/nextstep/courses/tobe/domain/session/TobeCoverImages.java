package nextstep.courses.tobe.domain.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TobeCoverImages {
    private final List<TobeCoverImage> coverImages;

    public TobeCoverImages(TobeCoverImage... coverImages) {
        this(List.of(coverImages));
    }

    public TobeCoverImages(List<TobeCoverImage> coverImages) {
        this.coverImages = new ArrayList<>(coverImages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeCoverImages that = (TobeCoverImages) o;
        return Objects.equals(coverImages, that.coverImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coverImages);
    }
}
