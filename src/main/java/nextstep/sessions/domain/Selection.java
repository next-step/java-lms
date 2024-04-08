package nextstep.sessions.domain;

public enum Selection {
    AUTOMATIC("자동", EnrollmentState.AUTO_APPROVAL),
    MANUAL("수동", EnrollmentState.PENDING);

    private String text;
    private EnrollmentState defaultEnrollmentState;

    Selection(String text, EnrollmentState defaultEnrollmentState) {
        this.text = text;
        this.defaultEnrollmentState = defaultEnrollmentState;
    }

    public String getText() {
        return text;
    }

    public EnrollmentState getDefaultEnrollmentState() {
        return defaultEnrollmentState;
    }

    public boolean isAutomatic() {
        return this == AUTOMATIC;
    }
}
