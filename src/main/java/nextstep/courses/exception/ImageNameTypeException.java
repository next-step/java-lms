package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.*;

public class ImageNameTypeException extends RuntimeException {

    private static final String MESSAGE = IMAGE_NAME_TYPE_EXCEPTION.getMessage();

    public ImageNameTypeException() {
        super(MESSAGE);
    }
}
