package nextstep.session.domain;

public enum SessionRecruitStatus {
    OPEN,
    CLOSED,
    ;

    SessionRecruitStatus() {
    }

    public static void enableOpen(SessionStatus status) {
        if (status.equals(SessionStatus.END)) {
            throw new IllegalArgumentException("모집중으로 변경할 수 없습니다.");
        }
    }

    public boolean isClosed() {
        return this == CLOSED;
    }
}
