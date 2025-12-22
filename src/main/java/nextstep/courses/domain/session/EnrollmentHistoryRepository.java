package nextstep.courses.domain.session;

import java.util.List;

public interface EnrollmentHistoryRepository {
    void save(EnrollmentHistory history);

    List<EnrollmentHistory> findBySessionIdAndUserId(Long sessionId, Long userId);

}
