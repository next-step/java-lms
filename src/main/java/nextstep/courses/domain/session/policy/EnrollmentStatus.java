package nextstep.courses.domain.session.policy;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EnrollmentStatus {
    NOT_ENROLLING("not_enrolling"),
    ENROLLING("enrolling");

    private final String status;

    EnrollmentStatus(String status) {
        this.status = status;
    }

    public static EnrollmentStatus fromString(String status) {
        return Arrays.stream(values())
            .filter(enrollmentStatus -> enrollmentStatus.status.equalsIgnoreCase(status))
            .findFirst()
            .orElse(NOT_ENROLLING);
    }

    public boolean canEnroll() {
        return this == ENROLLING;
    }
}
