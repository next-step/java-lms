package nextstep.session.domain;

public enum EnrollmentApprovalStatus {
    HOLD("대기"),
    APPROVED("ㅅ"),
    CANCELLED("취소");

    private final String description;

    EnrollmentApprovalStatus(String description) {
        this.description = description;
    }
}
