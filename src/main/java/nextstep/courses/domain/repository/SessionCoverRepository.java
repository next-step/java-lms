package nextstep.courses.domain.repository;

import nextstep.courses.domain.SessionCover;

public interface SessionCoverRepository {
    int save(SessionCover sessionCover);

    SessionCover findById(Long id);
}
