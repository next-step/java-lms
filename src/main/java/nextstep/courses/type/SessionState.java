package nextstep.courses.type;

public enum SessionState {
    PREPARING,
    ONGOING,
    END;

    public boolean ongoing() {
        return this == ONGOING;
    }
}
