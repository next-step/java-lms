package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Sessions;

public interface SessionRepository {

    int save(final Session session);

    Sessions findAllByCourseId(final Long courseId);
}
