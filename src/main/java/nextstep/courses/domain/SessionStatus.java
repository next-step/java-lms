package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING, RECRUITING, FINISHED;

    public static boolean isRecruiting(SessionStatus status) {
        return RECRUITING.equals(status);
    }
}
