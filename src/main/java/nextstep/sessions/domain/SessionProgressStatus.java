package nextstep.sessions.domain;

public enum SessionProgressStatus {
    PREPARING, PROGRESS, END;

    public boolean isEnd() {
        return this == END;
    }
}
