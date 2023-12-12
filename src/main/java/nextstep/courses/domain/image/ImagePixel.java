package nextstep.courses.domain.image;

import nextstep.courses.exception.ImagePixelRatioException;
import nextstep.courses.exception.ImagePixelSizeException;

import java.util.Objects;

public class ImagePixel {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = 3 / 2;

    private final int width;
    private final int height;

    public ImagePixel(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new ImagePixelSizeException();
        }
        if (width / height != RATIO) {
            throw new ImagePixelRatioException();
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null && getClass() != obj.getClass()) return false;
        ImagePixel imagePixel = (ImagePixel) obj;
        return width == imagePixel.width && height == imagePixel.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    public static ImagePixel of(int width, int height) {
        return new ImagePixel(width, height);
    }
}
