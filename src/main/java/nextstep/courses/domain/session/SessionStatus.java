package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING("준비중"),
    ONGOING("진행중"),
    END("종료");

    public final String description;

    SessionStatus(String description) {
        this.description = description;
    }
}
