package nextstep.courses.infrastructure;

import java.util.Optional;
import nextstep.courses.entity.SessionEntity;

public interface SessionRepository {

    int save(SessionEntity sessionEntity, Long courseId);

    Optional<SessionEntity> findById(Long id);

    int updateRegistrationCount(SessionEntity sessionEntity);
}
