package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionService {
    long save(Session session);

    Session findById(long id);

    long enroll(long id, List<NsUser> nsUsers);
}
