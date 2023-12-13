package nextstep.courses.repository;

import nextstep.courses.domain.Students;

public interface StudentsRepository {

    Long save(Long id, Long sessionId);

    Students findBySessionId(Long id);
}
