package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;

public class Session {

    private static final int UNLIMITED_ENROLLMENT = 0;
    private static final long FREE_FEE = 0L;
    private final Long id;
    private final CoverImage coverImage;
    private final Period period;
    private final int limitedEnrollment;
    private final SessionFee sessionFee;
    private SessionStatus sessionStatus;

    private Session(Long id, CoverImage coverImage, Period period, int limitedEnrollment, SessionFee sessionFee,
        SessionStatus sessionStatus) {
        this.id = id;
        this.sessionFee = sessionFee;
        this.coverImage = coverImage;
        this.period = period;
        this.limitedEnrollment = limitedEnrollment;
        this.sessionStatus = sessionStatus;
    }

    public Session(SessionStatus sessionStatus) {
        this(null, null, null, 0, new SessionFee(FREE_FEE), sessionStatus);
    }

    public static Session createFreeSession(CoverImage coverImage, Period period) {
        SessionStatus sessionStatus = updateSessionStatusToEnrollmentOpenOrPreparing(period);
        return new Session(null, coverImage, period, UNLIMITED_ENROLLMENT, new SessionFee(FREE_FEE), sessionStatus);
    }

    public static Session createPaidSession(CoverImage coverImage, Period period, int limitedEnrollment,
        SessionFee sessionFee) {
        SessionStatus sessionStatus = updateSessionStatusToEnrollmentOpenOrPreparing(period);
        return new Session(null, coverImage, period, limitedEnrollment, sessionFee, sessionStatus);
    }

    public int limitedEnrollment() {
        return limitedEnrollment;
    }

    public boolean isOpened() {
        return sessionStatus.equals(SessionStatus.ENROLLMENT_OPEN);
    }

    public boolean isEnrollmentAmountValid(Payment payment) {
        if (sessionFee.isFree()) {
            return true;
        }
        return payment.isSameAmount(sessionFee);
    }

    public boolean isExceededMaxEnrollment(int enrollmentCount) {
        if (sessionFee.isFree()) {
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
