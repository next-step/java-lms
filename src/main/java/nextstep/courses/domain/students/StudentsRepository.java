package nextstep.courses.domain.students;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public interface StudentsRepository {

    Students findBySession(Session session);

    void save(Session session, NsUser nsUser);
}
