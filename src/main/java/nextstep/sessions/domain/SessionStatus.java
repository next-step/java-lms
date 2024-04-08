package nextstep.sessions.domain;

public enum SessionStatus {
    PREPARING,
    RECRUITING,
    END;

    public static boolean isNotRecruiting(SessionStatus sessionStatus) {
        return sessionStatus != RECRUITING;
    }
}
