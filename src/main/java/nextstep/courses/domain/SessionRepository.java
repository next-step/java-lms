package nextstep.courses.domain;

import java.util.Optional;
import nextstep.courses.dto.SessionDTO;

public interface SessionRepository {
    int save(SessionDTO session);
    Session findById(Long id);
    Optional<Sessions> findByCourseId(Long courseId);
}
