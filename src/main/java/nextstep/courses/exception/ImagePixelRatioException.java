package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.IMAGE_PIXEL_RATIO_EXCEPTION;

public class ImagePixelRatioException extends RuntimeException {

    private static final String MESSAGE = IMAGE_PIXEL_RATIO_EXCEPTION.getMessage();

    public ImagePixelRatioException() {
        this(MESSAGE);
    }

    public ImagePixelRatioException(String message) {
        super(message);
    }
}
