package nextstep.courses.domain.repository;

import nextstep.courses.domain.entity.NsSession;

public interface SessionRepository {
    int save(NsSession nsSession);

    NsSession findById(Long id);
}
