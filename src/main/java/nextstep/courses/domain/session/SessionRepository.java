package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public interface SessionRepository {

    Session findById(Long id);

    void save(Session session);

}
