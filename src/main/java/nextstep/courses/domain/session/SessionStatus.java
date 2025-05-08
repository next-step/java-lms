package nextstep.courses.domain.session;

public enum SessionStatus {
    // 준비중, 모집중, 종료
    PREPARING, OPEN, CLOSED;

    public boolean isOpen() {
        return this == OPEN;
    }
}
