package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.exception.CourseExceptionMessage.INVALID_SESSION_STUDENT_ENROLLMENT_STATUS;

public class StudentEnrollmentStatusInvalidException extends CourseException {
    public StudentEnrollmentStatusInvalidException(String status) {
        super(INVALID_SESSION_STUDENT_ENROLLMENT_STATUS,
                MessageFormat.format("수강생 신청 상태: {0}", status));
    }
}
