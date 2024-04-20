package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface StudentRepository {
    int save(NsUser student, Session session);

    List<NsUser> findNsUsersBySessionRegisterDetailsId(long sessionId);
}
