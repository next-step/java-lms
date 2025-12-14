package nextstep.courses.domain.session.constant;

import java.util.Arrays;

public enum EnrollmentStatus {
    WAITING("대기"),
    APPROVED("승인"),
    CANCELLED("취소");

    private final String value;

    EnrollmentStatus(String value) {
        this.value = value;
    }

    public static EnrollmentStatus from(String name){
        return Arrays.stream(EnrollmentStatus.values())
                .filter(status -> status.matchStatus(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상태명을 잘못 입력하였습니다."));
    }

    private boolean matchStatus(String value) {
        return this.value.equals(value);
    }

    public boolean isCancled() {
        return this.equals(CANCELLED);
    }

    public boolean isApproved() {
        return this.equals(APPROVED);
    }
}
