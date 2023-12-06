package nextstep.courses.repository;

import nextstep.courses.domain.PaidSession;

public interface PaidSessionRepository {

    int save(PaidSession session, Long courseId);

    PaidSession findById(Long id);
}
