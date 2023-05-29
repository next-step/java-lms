package nextstep.courses.domain;


import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.users.domain.User;

import java.util.List;

public interface SessionRepository {
    Session findBySessionId(long id);

    long save(Session session, long courseId);

    void enrollUser(Session session);

    List<User> findUsersBySessionId(long sessionId);

    void updateApprovalStatus(long sessionId, long userId, ApprovalStatus approvalStatus);
}
