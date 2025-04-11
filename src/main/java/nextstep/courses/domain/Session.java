package nextstep.courses.domain;

public class Session {
    public static void checkRecruiting(SessionStatus sessionStatus) {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("본 강의는 수강생을 모집하고 있지 않습니다.");
        }
    }
}
