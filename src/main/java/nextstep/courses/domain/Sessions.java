package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class Sessions {

    private final List<Session> sessions = new ArrayList<>();

    public void addSession(Session session) {
        if (session == null) {
            throw new NullPointerException();
        }

        sessions.add(session);
    }

    public void enrolment(NsUser student, long sessionId) throws IllegalArgumentException {
        findSession(sessionId).enrolment(student);
    }

    public SessionStatus startRecruiting(long sessionId) {
        return findSession(sessionId).startRecruiting();
    }

    private Session findSession(long sessionId) {
        Optional<Session> session = sessions.stream().filter(s -> s.isSessionWithId(sessionId)).findFirst();

        if (session.isEmpty()) {
            throw new IllegalArgumentException("찾을 수 없는 강의 입니다.");
        }

        return session.get();
    }

    public SessionStatus startSession(long sessionId) {
        return findSession(sessionId).startSession();
    }

    public SessionStatus endSession(long sessionId) {
        return findSession(sessionId).endSession();
    }
}
