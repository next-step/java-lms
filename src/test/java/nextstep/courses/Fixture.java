package nextstep.courses;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class Fixture {
    public Session getSession1() {
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime toDate = fromDate.plusDays(7);
        int maxEnrollment = 1;
        String sessionCoverImagePath = "/course/math/main.png";

        SessionPeriod sessionPeriod = new SessionPeriod(fromDate, toDate);
        SessionCoverImage sessionCoverImage = new SessionCoverImage(sessionCoverImagePath);
        SessionUser sessionUser = new SessionUser(maxEnrollment);

        Session session = new Session(sessionPeriod, sessionCoverImage, SessionStatus.OPEN, SessionPayment.FREE, sessionUser);
        return session;
    }

    public Session getSession2() {
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime toDate = fromDate.plusDays(3);
        int maxEnrollment = 2;
        String sessionCoverImagePath = "/course/english/main.png";

        SessionPeriod sessionPeriod = new SessionPeriod(fromDate, toDate);
        SessionCoverImage sessionCoverImage = new SessionCoverImage(sessionCoverImagePath);
        SessionUser sessionUser = new SessionUser(maxEnrollment);

        Session session = new Session(sessionPeriod, sessionCoverImage, SessionStatus.OPEN, SessionPayment.PAID, sessionUser);
        return session;
    }
}
