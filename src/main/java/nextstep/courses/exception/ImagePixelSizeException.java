package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.IMAGE_PIXEL_SIZE_EXCEPTION;

public class ImagePixelSizeException extends RuntimeException {

    private static final String MESSAGE = IMAGE_PIXEL_SIZE_EXCEPTION.getMessage();

    public ImagePixelSizeException() {
        this(MESSAGE);
    }

    public ImagePixelSizeException(String message) {
        super(message);
    }
}
