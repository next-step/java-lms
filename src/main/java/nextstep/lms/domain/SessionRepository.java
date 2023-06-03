package nextstep.lms.domain;

import java.util.Optional;

public interface SessionRepository {
    int save(Session session);

    void changeImage(Session session);

    Optional<Session> findById(Long id);

    void changeSessionState(Session session);

    void updateRegisteredStudent(Session session);

    void changeSessionPaidType(Session session);

    void changeSessionRecruitingState(Session session);
}
