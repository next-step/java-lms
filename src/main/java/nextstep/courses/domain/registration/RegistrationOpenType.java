package nextstep.courses.domain.registration;

public enum RegistrationOpenType {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    private String code;

    RegistrationOpenType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
