package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.EnrollmentConditions;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionCoverImage;
import nextstep.courses.exception.SessionException;

public class SessionFixture {

    private static final Long FEE = 800_000L;

    public static SessionCoverImage coverImage() throws SessionException {
        return new SessionCoverImage(1024*1024, 300, 200, "gif");
    }

    public static Session session() throws SessionException {
        return new Session(new SessionCapacity(100), coverImage(), FEE, new EnrollmentConditions());
    }

    public static Session session(SessionCapacity capacity) throws SessionException {
        return new Session(capacity, coverImage(), FEE, new EnrollmentConditions());
    }

    public static Session session(SessionCapacity capacity, Long fee, EnrollmentConditions enrollmentConditions) throws SessionException {
        return new Session(capacity, coverImage(), fee, enrollmentConditions);
    }
}
