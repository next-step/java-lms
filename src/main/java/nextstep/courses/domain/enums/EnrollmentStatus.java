package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum EnrollmentStatus {
    NOT_ENROLLING("비모집중"),
    ENROLLING("모집중");

    private String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public boolean canEnroll() {
        return this == ENROLLING;
    }

    public static EnrollmentStatus of(String enrollmentStatus) {
        return Arrays.stream(values())
                .filter(e -> e.toString().equals(enrollmentStatus))
                .findFirst()
                .orElse(NOT_ENROLLING);
    }
}