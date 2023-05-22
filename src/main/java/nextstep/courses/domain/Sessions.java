package nextstep.courses.domain;

import nextstep.courses.RegisterCourseException;

import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void registerSession(Long sessionId, int studentsCount) {
        this.sessions.stream()
                .filter(session -> session.isSession(sessionId))
                .findFirst()
                .orElseThrow(() -> new RegisterCourseException("해당하는 강의가 존재하지 않습니다."))
                .registerSession(studentsCount);
    }
}
