package nextstep.courses.domain;

import nextstep.courses.dto.SessionDTO;

public interface SessionRepository {
    int save(SessionDTO session);
    Session findById(Long id);
}
