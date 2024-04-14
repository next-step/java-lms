package nextstep.courses.domain;

public enum SessionStatus {
    PREPARE,
    RECRUIT,
    END;


    public boolean isRecruit() {
        return this.equals(RECRUIT);
    }
}
