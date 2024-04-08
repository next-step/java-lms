package nextstep.courses.exception;

import nextstep.courses.domain.session.SessionStatus;

import java.text.MessageFormat;

import static nextstep.courses.exception.SessionExceptionMessage.*;

public class CannotEnrollmentSessionStatusException extends SessionException {

    public CannotEnrollmentSessionStatusException(SessionStatus status) {
        super(CANNOT_ENROLLMENT_SESSION_STATUS,
                MessageFormat.format("강의 상태: {0}", status));
    }

}
