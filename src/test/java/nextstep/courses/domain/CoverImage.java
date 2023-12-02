package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;

public class CoverImage {
    public CoverImage(long size) {
        validateImageSize(size);
    }

    private void validateImageSize(long size) {
        if (size > 1) {
            throw new ImageSizeOverException(size);
        }
    }
}
