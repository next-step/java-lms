package nextstep.courses.domain;

import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.Student;
import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {
    long save(Session session);

    Session findById(Long sessionId);

}
