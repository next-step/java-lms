package nextstep.courses.type;

public enum ProgressState {
    PREPARING,
    ONGOING,
    END;

    public boolean ongoing() {
        return this == ONGOING;
    }
}
