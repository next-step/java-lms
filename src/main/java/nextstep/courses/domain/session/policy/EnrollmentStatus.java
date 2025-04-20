package nextstep.courses.domain.session.policy;

import lombok.Getter;

@Getter
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
        return NOT_ENROLLING;
    }
}
