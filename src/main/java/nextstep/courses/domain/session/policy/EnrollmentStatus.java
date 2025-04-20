package nextstep.courses.domain.session.policy;

public enum EnrollmentStatus {
    NOT_ENROLLING("not_enrolling"),
    ENROLLING("enrolling");

    private final String status;

    EnrollmentStatus(String status) {
        this.status = status;
    }

    public boolean canEnroll() {
        return this == ENROLLING;
    }

    public static EnrollmentStatus fromString(String status) {
        for (EnrollmentStatus s : values()) {
            if (s.status.equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException(String.format("'%s'은(는) 유효한 모집 상태가 아닙니다.", status));
    }
}
