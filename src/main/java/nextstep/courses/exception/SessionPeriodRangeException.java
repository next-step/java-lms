package nextstep.courses.exception;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static nextstep.courses.exception.CourseExceptionMessage.INVALID_SESSION_DURATION;

public class SessionPeriodRangeException extends CourseException {

    public SessionPeriodRangeException(LocalDateTime startAt, LocalDateTime endAt) {
        super(INVALID_SESSION_DURATION, MessageFormat.format("(입력된 시작일시: {0}, 입력된 종료일시: {1})", startAt, endAt));
    }

}
