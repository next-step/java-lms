package nextstep.courses.domain.enums;

public enum EnrollmentStatus {
    NOT_ENROLLING("비모집중"),
    ENROLLING("모집중");

    private String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean canEnroll() {
        return this == ENROLLING;
    }
}