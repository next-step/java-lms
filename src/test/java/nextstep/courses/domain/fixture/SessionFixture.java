package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.Session;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionCourseFixture.course;
import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImage;

public class SessionFixture {

    public static Session session() {
        return new Session(SESSION_ID, course(), coverImage());
    }

}
