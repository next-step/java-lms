package nextstep.courses.domain.session;

public enum SessionRegisterStatus {
    APPROVED("승인됨"),
    WAITING("대기중"),
    REJECTED("취소됨");

    public final String description;

    SessionRegisterStatus(String description) {
        this.description = description;
    }
}
