package nextstep.courses.domain.registration;

public enum RegistrationOpenType {
    OPEN("OPEN", "모집중"),
    CLOSE("CLOSE", "비모집중");

    private String code;
    private String description;

    RegistrationOpenType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
