package nextstep.courses.domain.enrollment;

import java.util.Arrays;

public enum EnrollmentStatus {
    PENDING("대기중"),
    APPROVED("승인됨"),
    CANCELLED("취소됨");

    private final String value;

    EnrollmentStatus(String value) {
        this.value = value;
    }

    public static EnrollmentStatus from(String description) {
        return Arrays.stream(values())
                .filter(status -> status.value.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 수강신청 상태입니다: " + description));
    }

    public boolean isApproved() {
        return this == APPROVED;
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public String getValue() {
        return value;
    }
}
