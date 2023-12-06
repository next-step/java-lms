package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class ExceedLimitedVolumeException extends IllegalArgumentException {

    private static final String MESSAGE = EXCEED_LIMITED_VOLUME.getMessage();

    public ExceedLimitedVolumeException() {
        this(MESSAGE);
    }

    public ExceedLimitedVolumeException(long volume) {
        this(MESSAGE + DELIMITER + volume);
    }

    public ExceedLimitedVolumeException(String s) {
        super(s);
    }

}
