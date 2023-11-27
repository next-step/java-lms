package nextstep.courses.domain.code;

public enum EnrollType {

    FREE("무료 강의"),
    PAID("유료 강의");

    private final String description;

    EnrollType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
