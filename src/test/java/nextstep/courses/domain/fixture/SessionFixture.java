package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.*;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;

import static nextstep.courses.domain.session.SessionStatus.*;

public class SessionFixture {

    private static final Long FEE = 800_000L;
    private static final String PAYMENT_ID = "1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed";

    public static SessionCoverImage coverImage() throws SessionException {
        return new SessionCoverImage(1024*1024, 300, 200, "gif");
    }

    public static Session session() throws SessionException {
        return new Session(new SessionCapacity(100), coverImage(), FEE, RECRUITING, new EnrollmentConditions());
    }

    public static Session session(SessionCapacity capacity) throws SessionException {
        return new Session(capacity, coverImage(), FEE, RECRUITING, new EnrollmentConditions());
    }

    public static Session session(EnrollmentConditions enrollmentConditions) throws SessionException {
        return new Session(new SessionCapacity(100), coverImage(), FEE, RECRUITING, enrollmentConditions);
    }

    public static Session session(SessionCapacity capacity, Long fee, EnrollmentConditions enrollmentConditions) throws SessionException {
        return new Session(capacity, coverImage(), fee, RECRUITING, enrollmentConditions);
    }

    public static Payment payment() {
        return new Payment(PAYMENT_ID, 0L, 0L, FEE);
    }

    public static Payment payment(long amount) {
        return new Payment(PAYMENT_ID, 0L, 0L, amount);
    }

}
