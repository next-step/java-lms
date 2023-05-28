package nextstep.courses.domain;

import java.util.List;

public class Sessions {

    private static String NO_SESSION_ERROR_MESSAGE = "해당하는 강의가 존재하지 않습니다.";

    private List<Session> sessions;

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void registerSession(Long sessionId, int studentCount) {
        this.sessions.stream()
                .filter(session -> session.isSession(sessionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SESSION_ERROR_MESSAGE))
                .registerSession(studentCount);
    }
}
