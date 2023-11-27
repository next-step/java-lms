package nextstep.courses.domain.code;

public enum EnrollmentType {

    FREE("무료 강의"),
    PAID("유료 강의");

    private final String description;

    EnrollmentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
