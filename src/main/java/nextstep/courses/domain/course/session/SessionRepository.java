package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(Long id);

    Session save(Long courseId, Session session);

    int saveApply(Apply apply);

    Optional<Apply> findApplyByIds(Long NsUserId, Long sessionId);

    int update(Long sessionId, Session session);

    Sessions findAllByCourseId(Long courseId);

    int updateCourse(Long courseId, Session session);
}
