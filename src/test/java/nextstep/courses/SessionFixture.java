package nextstep.courses;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;

import java.time.LocalDateTime;

public class SessionFixture {
    public static Session 강의_과정_1() {
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime toDate = fromDate.plusDays(7);
        int maxEnrollment = 1;
        String sessionCoverImagePath = "/course/math/main.png";

        SessionPeriod sessionPeriod = new SessionPeriod(fromDate, toDate);
        SessionCoverImage sessionCoverImage = new SessionCoverImage(sessionCoverImagePath);
        SessionUsers sessionUsers = new SessionUsers(maxEnrollment);
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Session session = new Session(1L, sessionPeriod, sessionCoverImage, SessionStatus.OPEN, SessionPayment.FREE, sessionUsers, createdAt, updatedAt);
        session.enrollSession(NsUserTest.JAVAJIGI);
        return session;
    }

    public static Session 강의_과정_2() {
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime toDate = fromDate.plusDays(3);
        int maxEnrollment = 2;
        String sessionCoverImagePath = "/course/english/main.png";

        SessionPeriod sessionPeriod = new SessionPeriod(fromDate, toDate);
        SessionCoverImage sessionCoverImage = new SessionCoverImage(sessionCoverImagePath);
        SessionUsers sessionUser = new SessionUsers(maxEnrollment);
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Session session = new Session(2L, sessionPeriod, sessionCoverImage, SessionStatus.OPEN, SessionPayment.PAID, sessionUser, createdAt, updatedAt);
        session.enrollSession(NsUserTest.JAVAJIGI);
        session.enrollSession(NsUserTest.SANJIGI);
        return session;
    }
}
