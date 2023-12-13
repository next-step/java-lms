package nextstep.courses.domain;

import nextstep.courses.exception.EmptyCoverImageException;

import java.util.List;

public class CoverImages {

    private List<CoverImage> images;

    public CoverImages(List<CoverImage> images) {
        validateIsNull(images);
        this.images = images;
    }

    private void validateIsNull(List<CoverImage> images) {
        if (images == null || images.isEmpty()) {
            throw new EmptyCoverImageException();
        }
    }
}
