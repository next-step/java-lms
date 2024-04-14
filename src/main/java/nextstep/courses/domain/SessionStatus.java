package nextstep.courses.domain;

public enum SessionStatus {
    READY,
    RECRUITING,
    END;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
