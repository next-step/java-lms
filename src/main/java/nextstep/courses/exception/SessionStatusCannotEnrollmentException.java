package nextstep.courses.exception;

import nextstep.courses.domain.enrollment.SessionStatus;

import java.text.MessageFormat;

import static nextstep.courses.exception.CourseExceptionMessage.*;

public class SessionStatusCannotEnrollmentException extends CourseException {

    public SessionStatusCannotEnrollmentException(SessionStatus status) {
        super(CANNOT_ENROLLMENT_SESSION_STATUS,
                MessageFormat.format("강의 상태: {0}", status));
    }

}
