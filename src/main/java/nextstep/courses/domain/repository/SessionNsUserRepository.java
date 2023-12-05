package nextstep.courses.domain.repository;

import java.util.List;

public interface SessionNsUserRepository {



    boolean save(Long sessionId, Long nsUserId);

    List<Integer> findBySessionId(Long sessionId);

    int findByNsUserId(Long nsUserId);
}
