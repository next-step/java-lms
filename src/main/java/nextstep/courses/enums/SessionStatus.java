package nextstep.courses.enums;

public enum SessionStatus {
    PREPARE,
    RECRUIT,
    END;

    public boolean isStatusNotRecruit() {
        return this != SessionStatus.RECRUIT;
    }

}
