package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.IMAGE_SIZE_EXCEPTION;

public class ImageSizeException extends RuntimeException {

    private static final String MESSAGE = IMAGE_SIZE_EXCEPTION.getMessage();

    public ImageSizeException() {
        super(MESSAGE);
    }
}
