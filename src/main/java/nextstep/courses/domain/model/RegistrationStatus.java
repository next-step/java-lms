package nextstep.courses.domain.model;

public enum RegistrationStatus {
    OPEN, CLOSE;

    public boolean isNotSupport() {
        return this != RegistrationStatus.OPEN;
    }
}
