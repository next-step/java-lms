package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.MAX_PARTICIPANTS_EXCEPTION;

public class MaxParticipantsException extends RuntimeException {

    private static final String MESSAGE = MAX_PARTICIPANTS_EXCEPTION.getMessage();

    public MaxParticipantsException() {
        super(MESSAGE);
    }
}
