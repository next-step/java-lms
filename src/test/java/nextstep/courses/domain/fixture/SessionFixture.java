package nextstep.courses.domain.fixture;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.enrollment.SessionStatus;

import static nextstep.courses.domain.fixture.IdFixture.COURSE_ID;
import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImage;

public class SessionFixture {

    public static Session session(SessionType type) {
        return new Session(COURSE_ID, type, coverImage(), SessionStatus.RECRUITING);
    }

}
