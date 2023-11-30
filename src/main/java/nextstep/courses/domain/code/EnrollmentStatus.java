package nextstep.courses.domain.code;

import nextstep.courses.exception.SessionClosedException;

public enum EnrollmentStatus {
    RECRUITING,
    CLOSED;

    public void validateEnroll() {
        if (this == EnrollmentStatus.CLOSED) {
            throw new SessionClosedException("모집 종료된 강의 입니다.");
        }
    }
}
