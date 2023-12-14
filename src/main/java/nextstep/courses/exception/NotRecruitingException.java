package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.NOT_RECRUITING_EXCEPTION;

public class NotRecruitingException extends RuntimeException {

    private static final String MESSAGE = NOT_RECRUITING_EXCEPTION.getMessage();

    public NotRecruitingException() {
        super(MESSAGE);
    }
}
