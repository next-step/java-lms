package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionStudents {

    private List<SessionStudent> sessionStudents;

    public SessionStudents() {
        this.sessionStudents = new ArrayList<>();
    }

    public boolean add(Session session, NsUser student) {
        return this.sessionStudents.add(new SessionStudent(session, student));
    }

    public boolean isOver(int limit) {
        return sessionStudents.size() >= limit;
    }
}
