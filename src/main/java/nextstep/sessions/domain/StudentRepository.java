package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

public interface StudentRepository {
    int save(NsUser student, Session session);

    Student findByNsUserId(long nsUserId);

    Student findBySessionId(long SessionId);
}
