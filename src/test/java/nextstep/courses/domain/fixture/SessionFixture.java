package nextstep.courses.domain.fixture;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.EnrollmentConditions;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.SessionStatus.RECRUITING;

public class SessionFixture {

    private static final Long FEE = 800_000L;
    private static final String PAYMENT_ID = "1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed";
    private static final LocalDateTime SESSION_START_AT = LocalDateTime.now().plusDays(10);
    private static final LocalDateTime SESSION_END_AT = SESSION_START_AT.plusMonths(2);

    public static CoverImage coverImage() throws SessionException {
        return new CoverImage(1024*1024, 300, 200, "gif");
    }

    public static Session session() throws SessionException {
        return new Session(course(), SESSION_START_AT, SESSION_END_AT, new SessionCapacity(100), coverImage(), FEE, RECRUITING, new EnrollmentConditions());
    }

    public static Session session(SessionCapacity capacity) throws SessionException {
        return new Session(course(), SESSION_START_AT, SESSION_END_AT, capacity, coverImage(), FEE, RECRUITING, new EnrollmentConditions());
    }

    public static Session session(EnrollmentConditions enrollmentConditions) throws SessionException {
        return new Session(course(), SESSION_START_AT, SESSION_END_AT, new SessionCapacity(100), coverImage(), FEE, RECRUITING, enrollmentConditions);
    }

    public static Session session(SessionCapacity capacity, Long fee, EnrollmentConditions enrollmentConditions) throws SessionException {
        return new Session(course(), SESSION_START_AT, SESSION_END_AT, capacity, coverImage(), fee, RECRUITING, enrollmentConditions);
    }

    public static Payment payment() {
        return new Payment(PAYMENT_ID, 0L, 0L, FEE);
    }

    public static Payment payment(long amount) {
        return new Payment(PAYMENT_ID, 0L, 0L, amount);
    }

    public static Course course() {
        return new Course("TDD, 클린 코드 with Java 18기", 0L);
    }

}