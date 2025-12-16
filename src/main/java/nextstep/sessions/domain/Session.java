package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

    public static final String ERROR_SESSION_NOT_OPEN = "모집중인 강의만 수강 신청 가능합니다.";
    public static final String ERROR_CAPACITY_EXCEEDED = "강의 정원이 초과되어 수강 신청할 수 없습니다.";
    public static final String ERROR_PAYMENT_AMOUNT_MISMATCH = "결제 금액이 강의 수강료와 일치하지 않습니다.";

    private Long id;

    private SessionInfo sessionInfo;

    private SessionStatus status;

    private final SessionPricing pricing;

    private Capacity capacity;

    private final Enrollments enrollments = new Enrollments();

    Session(Long id, SessionInfo sessionInfo, SessionPricing pricing, Capacity capacity) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.status = SessionStatus.PREPARING;
        this.pricing = pricing;
        this.capacity = capacity;
    }

    public static Session paidLimited(Long id, LocalDate startDate, LocalDate endDate, int fee, int maxCapacity,
                                      SessionImage image) {
        SessionInfo info = new SessionInfo(new Period(startDate, endDate), image);
        return new Session(id, info, SessionPricing.paid(fee), Capacity.limited(maxCapacity));
    }

    public static Session freeUnlimited(Long id, LocalDate startDate, LocalDate endDate, SessionImage image) {
        SessionInfo info = new SessionInfo(new Period(startDate, endDate), image);
        return new Session(id, info, SessionPricing.free(), Capacity.unlimited());
    }

    public SessionStatus status() {
        return status;
    }

    public void startRecruiting() {
        this.status = SessionStatus.OPEN;
    }

    public void enroll(Enrollment enrollment) {
        validateOpen();
        validateCapacity();
        validatePaymentAmount(enrollment);

        enrollments.add(enrollment);
        capacity = capacity.increaseEnrollCount();
    }

    private void validateOpen() {
        if (!isOpen()) {
            throw new IllegalStateException(ERROR_SESSION_NOT_OPEN);
        }
    }

    private void validateCapacity() {
        if (capacity.isFull()) {
            throw new IllegalStateException(ERROR_CAPACITY_EXCEEDED);
        }
    }

    private void validatePaymentAmount(Enrollment enrollment) {
        if (!enrollment.canPayFor(pricing)) {
            throw new IllegalArgumentException(ERROR_PAYMENT_AMOUNT_MISMATCH);
        }
    }

    private boolean isOpen() {
        return status == SessionStatus.OPEN;
    }

}
