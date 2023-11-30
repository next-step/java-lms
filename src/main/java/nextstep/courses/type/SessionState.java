package nextstep.courses.type;

public enum SessionState {
    PREPARING,
    RECRUITING,
    END;

    public static boolean recruiting(SessionState sessionState) {
        return sessionState == SessionState.RECRUITING;
    }
}
