package nextstep.courses.domain.session;

public enum SessionStatus {
    READY("준비중"),
    RECRUITING("모집중"),
    ONGOING("진행중"),
    CLOSED("종료");

    private final String displayName;

    SessionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
