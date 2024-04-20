package nextstep.courses.exception;

import nextstep.courses.domain.session.SessionType;

import java.text.MessageFormat;

public class SessionEnrollmentNotMatchException extends CourseException {
    public SessionEnrollmentNotMatchException(SessionType type) {
        super(CourseExceptionMessage.NOT_MATCH_SESSION_ENROLLMENT, MessageFormat.format("입력된 유형: {0}", type));
    }
}
