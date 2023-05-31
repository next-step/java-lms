package nextstep.courses.domain.registration;

public enum RegistrationOpenType {
    OPEN("open"),
    CLOSE("close");

    private String code;

    RegistrationOpenType(String code) {
        this.code = code;
    }
}
