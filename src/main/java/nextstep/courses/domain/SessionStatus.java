package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING,
    RECRUITING,
    END;

    public boolean canEnroll() {
        return this == RECRUITING;
    }
}
