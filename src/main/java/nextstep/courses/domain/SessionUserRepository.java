package nextstep.courses.domain;

import java.util.List;

public interface SessionUserRepository {
    int saveAll(List<SessionUser> sessionUsers);

    List<SessionUser> findBySession(Long id);
}
