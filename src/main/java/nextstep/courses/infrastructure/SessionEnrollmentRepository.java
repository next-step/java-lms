package nextstep.courses.infrastructure;

import java.util.List;

public interface SessionEnrollmentRepository {
    void save(Long sessionId, Long userId);
    List<Long> findUserIdsBySessionId(Long sessionId);
} 