package nextstep.courses.domain;

import nextstep.courses.domain.session.Students;

public interface StudentsRepository {
    int saveAll(Students students);
    Students findAllBySessionId(long sessionId);
}
