package nextstep.courses.domain;

import java.util.List;
import java.util.Optional;

public interface SessionUserMappingRepository {
    int save(SessionUserMapping sessionUserMapping);

    SessionUserMapping findById(Long id);

    List<SessionUserMapping> findBySessionId(Long sessionId);

    int update(SessionUserMapping sessionUserMapping);
    Optional<List<SessionUserMapping>> findBySessionIds(List<Long> sessionIds);

    int deleteById(Long id);

    int deleteBySessionId(Long sessionId);

}
