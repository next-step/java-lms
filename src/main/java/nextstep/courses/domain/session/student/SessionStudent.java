package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class SessionStudent {

    private Long id;
    private Session session;
    private NsUser stuent;

    protected SessionStudent(Session session, NsUser stuent) {
        this.session = session;
        this.stuent = stuent;
    }
}
