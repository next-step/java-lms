package nextstep.courses.exception;

import java.time.LocalDateTime;

import static nextstep.courses.exception.ExceptionMessage.*;

public class NotCorrectTimeException extends IllegalArgumentException {

    private static final String MESSAGE = NOT_CORRECT_TIME.getMessage();

    public NotCorrectTimeException() {
        this(MESSAGE);
    }

    public NotCorrectTimeException(LocalDateTime startAt, LocalDateTime endAt) {
        this(String.format("%s\n%s\n%s", MESSAGE, startAt, endAt));
    }

    public NotCorrectTimeException(String s) {
        super(s);
    }
}
