package nextstep.courses.domain;

import nextstep.courses.exception.NotFoundSessionException;

import java.util.ArrayList;
import java.util.List;

public class Instructor {

    private final List<Session> sessions;

    public Instructor() {
        this.sessions = new ArrayList<>();
    }

    public Instructor(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void approve(Long sessionId, Long studentId) {
        Session session = session(sessionId);
        session.approve(studentId);
    }

    public void refuse(Long sessionId, Long studentId) {
        Session session = session(sessionId);
        session.refuse(studentId);
    }

    private Session session(Long id) {
        return sessions.stream()
            .filter(session -> session.equalId(id))
            .findFirst()
            .orElseThrow(() -> new NotFoundSessionException("강의를 찾을 수 없습니다"));
    }

}
