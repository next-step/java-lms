package nextstep.courses.domain.registration;

public enum RegistrationStatus {
    PENDING("pending"),
    APPROVED("approved"),
    CANCELED("canceled");

    private String code;

    RegistrationStatus(String code) {
        this.code = code;
    }
}
