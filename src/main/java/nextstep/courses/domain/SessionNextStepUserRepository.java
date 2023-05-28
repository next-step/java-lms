package nextstep.courses.domain;

import nextstep.users.domain.User;

import java.util.List;

public interface SessionNextStepUserRepository {
    int save(long sessionId, long userId);

    List<User> findUsersBySessionId(long sessionId);
}
