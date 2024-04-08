package nextstep.courses.exception;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static nextstep.courses.exception.SessionExceptionMessage.INVALID_SESSION_DURATION;

public class InvalidSessionDurationException extends SessionException {

    public InvalidSessionDurationException(LocalDateTime startAt, LocalDateTime endAt) {
        super(INVALID_SESSION_DURATION, MessageFormat.format("(입력된 시작일시: {0}, 입력된 종료일시: {1})", startAt, endAt));
    }

}
