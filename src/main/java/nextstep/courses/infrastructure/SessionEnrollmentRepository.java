package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.enrollment.EnrollmentStatus;

import java.util.List;
import java.util.Map;

public interface SessionEnrollmentRepository {
    void save(Long sessionId, Long userId);
    void save(Long sessionId, Long userId, EnrollmentStatus status);
    void updateStatus(Long sessionId, Long userId, EnrollmentStatus status);
    List<Long> findUserIdsBySessionId(Long sessionId);
    Map<Long, EnrollmentStatus> findUserStatusesBySessionId(Long sessionId);
}
