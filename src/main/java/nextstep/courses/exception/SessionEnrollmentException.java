package nextstep.courses.exception;

import nextstep.courses.domain.enums.EnrollmentStatus;
import nextstep.courses.domain.enums.SessionStatus;

public class SessionEnrollmentException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "현재 강의 모집 상태는 '%s'이며, '%s' 상태에서만 수강 신청이 가능합니다.";
    private static final String MAXIMUM_ENROLLMENT_EXCEEDED_MESSAGE = "최대 수강 인원인 '%d명'을 초과했습니다.";


    public SessionEnrollmentException(String message) {
        super(message);
    }

    public SessionEnrollmentException(int maximumEnrollment) {
        super(String.format(MAXIMUM_ENROLLMENT_EXCEEDED_MESSAGE, maximumEnrollment));
    }

    public SessionEnrollmentException(SessionStatus sessionStatus, EnrollmentStatus requiredStatus) {
        super(String.format(DEFAULT_MESSAGE, sessionStatus, requiredStatus));
    }
}
