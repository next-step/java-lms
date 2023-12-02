package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;
import nextstep.courses.exception.InvalidImageExtensionException;

public class CoverImage {

    private static final int IMAGE_SIZE = 1;

    public CoverImage(long size, String extension) {
        validateImageSize(size);
        validateExtension(extension);
    }

    private void validateExtension(String extension) {
        if (!Extension.contains(extension)) {
            throw new InvalidImageExtensionException();
        }
    }

    private void validateImageSize(long size) {
        if (size > IMAGE_SIZE) {
            throw new ImageSizeOverException(size);
        }
    }
}
