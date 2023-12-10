package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.FreeOrPaid.FREE;
import static nextstep.courses.domain.session.FreeOrPaid.PAID;

import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;

public class Session {

    private static final int UNLIMITED_ENROLLMENT = 0;
    private static final long FREE_FEE = 0;
    private final Long id;
    private final FreeOrPaid freeOrPaid;
    private final CoverImage coverImage;
    private final Period period;
    private final int limitedEnrollment;
    private final long sessionFee;
    private SessionStatus sessionStatus;

    private Session(Long id, FreeOrPaid freeOrPaid, CoverImage coverImage, Period period, int limitedEnrollment,
        long sessionFee, SessionStatus sessionStatus) {
        this.id = id;
        this.freeOrPaid = freeOrPaid;
        this.sessionFee = sessionFee;
        this.coverImage = coverImage;
        this.period = period;
        this.limitedEnrollment = limitedEnrollment;
        this.sessionStatus = sessionStatus;
    }

    public Session(SessionStatus sessionStatus) {
        this(null, null, null, null, 0, 0, sessionStatus);
    }

    public static Session createFreeSession(CoverImage coverImage, Period period) {
        SessionStatus sessionStatus = updateSessionStatusToEnrollmentOpenOrPreparing(period);
        return new Session(null, FREE, coverImage, period, UNLIMITED_ENROLLMENT, FREE_FEE, sessionStatus);
    }

    public static Session createPaidSession(CoverImage coverImage, Period period, int limitedEnrollment,
        long sessionFee) {
        SessionStatus sessionStatus = updateSessionStatusToEnrollmentOpenOrPreparing(period);
        return new Session(null, PAID, coverImage, period, limitedEnrollment, sessionFee, sessionStatus);
    }

    public int limitedEnrollment() {
        return limitedEnrollment;
    }

    public boolean isOpened() {
        return sessionStatus.equals(SessionStatus.ENROLLMENT_OPEN);
    }

    public boolean isEnrollmentAmountValid(Payment payment) {
        if (FreeOrPaid.isFree(freeOrPaid)) {
            return true;
        }
        return payment.isSameAmount(sessionFee);
    }

    public boolean isExceededMaxEnrollment(int enrollmentCount) {
        if (FreeOrPaid.isFree(freeOrPaid)) {
            return false;
        }
        return enrollmentCount > limitedEnrollment;
    }

    private static SessionStatus updateSessionStatusToEnrollmentOpenOrPreparing(Period period) {
        if (period.isEqualStartDateAndToday()) {
            return SessionStatus.ENROLLMENT_OPEN;
        }
        return SessionStatus.PREPARING;
    }
}
