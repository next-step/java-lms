package nextstep.sessions.domain;

public enum EnrollmentState {
    AUTO_APPROVAL("자동승인"),
    PENDING("대기"),
    SELECTED("선별"),
    CANCELLED("취소");

    private final String text;

    EnrollmentState(String text) {
        this.text = text;
    }
}
