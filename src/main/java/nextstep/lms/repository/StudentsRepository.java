package nextstep.lms.repository;

import nextstep.lms.domain.CoverImage;
import nextstep.lms.domain.Students;

public interface StudentsRepository {
    int save(Long userId, Long sessionId);

    Students findBySessionId(Long sessionId);
}
