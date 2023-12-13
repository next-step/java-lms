package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class ImagesSizeZeroException extends IllegalArgumentException {

    private static final String MESSAGE = IMAGES_SIZE_ZERO.getMessage();

    public ImagesSizeZeroException() {
        this(MESSAGE);
    }

    public ImagesSizeZeroException(String s) {
        super(s);
    }
}
