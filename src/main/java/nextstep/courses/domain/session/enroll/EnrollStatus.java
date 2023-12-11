package nextstep.courses.domain.session.enroll;

public enum EnrollStatus {

    ENROLL_ON("모집중"),
    ENROLL_OFF("비모집중");

    private String description;

    EnrollStatus(String description) {
        this.description = description;
    }

    public static boolean isEnrollOff(EnrollStatus enrollStatus) {
        return ENROLL_OFF.equals(enrollStatus);
    }
}
