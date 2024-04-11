package nextstep.courses.domain.image;

import nextstep.courses.exception.SessionCoverImageException;

public class ImageSize {

    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    public static final int WIDTH_RATIO = MIN_WIDTH / 100;
    public static final int HEIGHT_RATIO = MIN_HEIGHT / 100;

    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    public void validate(int width, int height) {
        if (width > MIN_WIDTH || height > MIN_HEIGHT) {
            throw new SessionCoverImageException(width, height);
        }

        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new SessionCoverImageException(width, height);
        }
    }
}
