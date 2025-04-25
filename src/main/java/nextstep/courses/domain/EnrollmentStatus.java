package nextstep.courses.domain;

public enum EnrollmentStatus {
    REQUESTED, APPROVED, REJECTED;

    public EnrollmentStatus approve() {
        if (this == EnrollmentStatus.REJECTED) {
            throw new IllegalStateException("이미 반려된 신청 건 입니다.");
        }

        return APPROVED;
    }


    public EnrollmentStatus reject() {
        if (this == EnrollmentStatus.APPROVED) {
            throw new IllegalStateException("이미 승인된 신청 건 입니다.");
        }

        return REJECTED;
    }
}
