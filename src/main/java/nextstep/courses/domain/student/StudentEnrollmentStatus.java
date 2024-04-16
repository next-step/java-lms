package nextstep.courses.domain.student;

import nextstep.courses.exception.StudentEnrollmentStatusInvalidException;

import java.util.Arrays;

public enum StudentEnrollmentStatus {

    PENDING("대기중"),
    APPROVAL("승인"),
    CANCEL("취소")
    ;

    private final String status;

    StudentEnrollmentStatus(String status) {
        this.status = status;
    }

    public static StudentEnrollmentStatus convert(String status) {
        return Arrays.stream(values())
                .filter(enrollmentStatus -> enrollmentStatus.status.equals(status))
                .findAny()
                .orElseThrow(() -> new StudentEnrollmentStatusInvalidException(status));
    }

    public String get() {
        return status;
    }

}
