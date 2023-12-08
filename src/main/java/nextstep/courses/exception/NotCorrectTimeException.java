package nextstep.courses.exception;

import java.time.LocalDate;

import static nextstep.courses.exception.ExceptionMessage.*;

public class NotCorrectTimeException extends IllegalArgumentException {

    private static final String MESSAGE = NOT_CORRECT_TIME.getMessage();

    public NotCorrectTimeException() {
        this(MESSAGE);
    }

    public NotCorrectTimeException(LocalDate startAt, LocalDate endAt) {
        this(String.format("%s\n%s\n%s", MESSAGE, startAt, endAt));
    }

    public NotCorrectTimeException(String s) {
        super(s);
    }
}
