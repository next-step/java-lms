package nextstep.courses.domain.session;

import java.util.Optional;

import nextstep.courses.domain.course.Sessions;

public interface SessionRepository {

    long save(final Session session);

    void updateEnrollmentCount(final Session session);

    Optional<Session> findById(final Long id);

    Sessions findAllByCourseId(final Long courseId);
}
