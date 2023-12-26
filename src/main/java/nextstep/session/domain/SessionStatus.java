package nextstep.session.domain;

public enum SessionStatus {
    PREPARING,
    PROCEEDING,
    END;

    public boolean isEnd() {
        return this == END;
    }
}
