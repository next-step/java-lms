package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;

public class CoverImage {
    private static final int MAX_MB_OF_IMAGE_SIZE = 1;

    private final long size;
    private final Extension extension;
    private final ImageDimensions dimensions;

    public CoverImage(long size, String extension, int width, int height) {
        validateImageSize(size);
        this.size = size;
        this.extension = Extension.from(extension);
        this.dimensions = new ImageDimensions(width, height);
    }

    private void validateImageSize(long size) {
        if (size > MAX_MB_OF_IMAGE_SIZE) {
            throw new ImageSizeOverException(size);
        }
    }
}
