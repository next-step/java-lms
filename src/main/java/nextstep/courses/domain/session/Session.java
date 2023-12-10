package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.FreeOrPaid.FREE;
import static nextstep.courses.domain.session.FreeOrPaid.PAID;

import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;

public class Session {

    private static final int UNLIMITED_ENROLLMENT = 0;
    private static final long FREE_FEE = 0;
    private final FreeOrPaid freeOrPaid;
    private final CoverImage coverImage;
    private final Period period;
    private final int limitedEnrollment;
    private final long sessionFee;
    private SessionStatus sessionStatus;

    private Session(FreeOrPaid freeOrPaid, CoverImage coverImage, Period period, int limitedEnrollment,
        long sessionFee) {
        this.freeOrPaid = freeOrPaid;
        this.sessionFee = sessionFee;
        this.coverImage = coverImage;
        this.period = period;
        this.limitedEnrollment = limitedEnrollment;
        this.sessionStatus = updateSessionStatusToEnrollmentOpenOrPreparing();
    }

    public Session(Period period) {
        this(null, null, period, 0, 0);
    }

    public static Session createFreeSession(CoverImage coverImage, Period period) {
        return new Session(FREE, coverImage, period, UNLIMITED_ENROLLMENT, FREE_FEE);
    }

    public static Session createPaidSession(CoverImage coverImage, Period period, int limitedEnrollment,
        long sessionFee) {
        return new Session(PAID, coverImage, period, limitedEnrollment, sessionFee);
    }

    public int limitedEnrollment() {
        return limitedEnrollment;
    }

    public boolean isOpened() {
        return sessionStatus.equals(SessionStatus.ENROLLMENT_OPEN);
    }

    public boolean isAbleToEnrollPaidSession(Payment payment) {
        if (freeOrPaid == FREE) {
            return true;
        }
        return payment.amount() == sessionFee;
    }

    private SessionStatus updateSessionStatusToEnrollmentOpenOrPreparing() {
        if (period.isEqualStartDateAndToday()) {
            return SessionStatus.ENROLLMENT_OPEN;
        }
        return SessionStatus.PREPARING;
    }
}
