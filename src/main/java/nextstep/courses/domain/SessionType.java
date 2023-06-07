package nextstep.courses.domain;

public enum SessionType {
    READY, RECRUITING, END;

    public static boolean isRecruiting(SessionType sessionType) {
        return sessionType == RECRUITING;
    }
}
