package nextstep.courses.domain;

public enum SessionState {

    PREPARING, RECRUITING, CLOSE;

    public static boolean isRecruitable(SessionState sessionState) {
        return sessionState.equals(RECRUITING);
    }
}
