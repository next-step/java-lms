package nextstep.courses.domain.session.engine;

import nextstep.courses.domain.session.SessionCoverImage;
import nextstep.courses.domain.session.Students;

public abstract class ConcreteSession implements Session {

    protected final Long id;
    protected final Long courseId;
    protected final SessionCoverImage coverImage;
    protected final SessionEnrollment enrollment;
    protected final Students students;

    protected ConcreteSession(Long id, Long courseId, SessionCoverImage coverImage, SessionEnrollment enrollment, Students students) {
        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
        this.students = students;
    }

}
