package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public interface StudentsRepository {
    int save(long sessionId, NsUser student);

    int saveAll(long sessionId, Students students);

    Students findBySessionId(Long sessionId);
}
