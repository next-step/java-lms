package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.engine.Session;

import static nextstep.courses.domain.fixture.IdFixture.COURSE_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImage;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.freeSessionEnrollment;
import static nextstep.courses.domain.fixture.StudentFixture.students;

public class SessionFixture {

    public static Session freeSession() {
        return new FreeSession(SESSION_ID, COURSE_ID, coverImage(), freeSessionEnrollment(), students());
    }

}
