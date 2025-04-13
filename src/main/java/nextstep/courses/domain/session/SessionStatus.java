package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    CLOSED("종료");

    private final String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
} 