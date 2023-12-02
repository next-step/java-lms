package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;
import nextstep.courses.exception.InvalidImageWidthAndHeightException;

public class CoverImage {

    private static final int IMAGE_SIZE = 1;
    private static final int WIDTH_PIXEL = 300;
    private static final int HEIGHT_PIXEL = 200;
    private static final int WIDTH_RATIO = 2;
    private static final int HEIGHT_RATIO = 3;

    private final long size;
    private final Extension extension;
    private final int width;
    private final int height;

    public CoverImage(long size, String extension, int width, int height) {
        validateImageSize(size);
        validateWidthAndHeight(width, height);
        this.size = size;
        this.extension = Extension.of(extension);
        this.width = width;
        this.height = height;
    }

    private void validateImageSize(long size) {
        if (size > IMAGE_SIZE) {
            throw new ImageSizeOverException(size);
        }
    }

    private void validateWidthAndHeight(int width, int height) {
        if (!checkPixel(width, height) || !checkRatio(width, height)) {
            throw new InvalidImageWidthAndHeightException();
        }
    }

    private static boolean checkPixel(int width, int height) {
        return width >= WIDTH_PIXEL && height >= HEIGHT_PIXEL;
    }

    private static boolean checkRatio(int width, int height) {
        return width * WIDTH_RATIO == height * HEIGHT_RATIO;
    }
}
