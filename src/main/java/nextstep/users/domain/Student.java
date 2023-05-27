package nextstep.users.domain;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Sessions;

public class Student {
    private NsUser nsUser;
    private Sessions sessions;

    public Student(NsUser nsUser, Sessions sessions) {
        this.nsUser = nsUser;
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.addSession(session);
    }

    public Sessions getSessions() {
        return sessions;
    }
}
