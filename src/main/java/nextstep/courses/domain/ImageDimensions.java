package nextstep.courses.domain;

import nextstep.courses.exception.InvalidImageWidthAndHeightException;

import java.util.Objects;

public class ImageDimensions {
    private static final int MIN_WIDTH_PIXEL = 300;
    private static final int MIN_HEIGHT_PIXEL = 200;
    private static final int WIDTH_RATIO = 2;
    private static final int HEIGHT_RATIO = 3;

    private final int width;
    private final int height;

    public ImageDimensions(int width, int height) {
        validateWidthAndHeight(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateWidthAndHeight(int width, int height) {
        if (!checkPixel(width, height) || !checkRatio(width, height)) {
            throw new InvalidImageWidthAndHeightException();
        }
    }

    private static boolean checkPixel(int width, int height) {
        return width >= MIN_WIDTH_PIXEL && height >= MIN_HEIGHT_PIXEL;
    }

    private static boolean checkRatio(int width, int height) {
        return width * WIDTH_RATIO == height * HEIGHT_RATIO;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDimensions that = (ImageDimensions) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
