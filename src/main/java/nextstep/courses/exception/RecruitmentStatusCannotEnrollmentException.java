package nextstep.courses.exception;

import nextstep.courses.domain.status.RecruitmentStatus;

import java.text.MessageFormat;

import static nextstep.courses.exception.CourseExceptionMessage.CANNOT_ENROLLMENT_SESSION_STATUS;

public class RecruitmentStatusCannotEnrollmentException extends CourseException {

    public RecruitmentStatusCannotEnrollmentException(RecruitmentStatus status) {
        super(CANNOT_ENROLLMENT_SESSION_STATUS,
                MessageFormat.format("강의 상태: {0}", status));
    }

}
