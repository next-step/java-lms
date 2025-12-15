package nextstep.sessions.domain;

import java.time.LocalDate;
import nextstep.payments.domain.Payment;

public class Session {

    private SessionInfo sessionInfo;

    private SessionStatus status;

    private final SessionPricing pricing;

    private Capacity capacity;

    private EnrollmentPolicy enrollmentPolicy;

    Session(LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity, int fee, int enrollCount,
            SessionImage image) {
        this(startDate, endDate, isPaid, maxCapacity, fee, image);
    }

    public Session(LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity, int fee,
                   SessionImage image) {
        this(new SessionInfo(new Period(startDate, endDate), image),
                new SessionPricing(isPaid, fee), new Capacity(maxCapacity));
    }

    public Session(SessionInfo sessionInfo, SessionPricing pricing, Capacity capacity) {
        validatePricingAndCapacity(pricing, capacity);
        this.sessionInfo = sessionInfo;
        this.status = SessionStatus.PREPARING;
        this.pricing = pricing;
        this.capacity = capacity;
        this.enrollmentPolicy = createEnrollmentPolicy(pricing);
    }

    public SessionStatus status() {
        return status;
    }

    public boolean canEnroll(Payment payment) {
        return enrollmentPolicy.canEnroll(capacity, payment) && isOpen();
    }

    public void startRecruiting() {
        this.status = SessionStatus.OPEN;
    }

    public void enroll(Payment payment) {
        if (!canEnroll(payment)) {
            throw new IllegalArgumentException("수강 신청을 할 수 없습니다");
        }
        this.capacity = capacity.increaseEnrollCount();
    }

    private void validatePricingAndCapacity(SessionPricing pricing, Capacity capacity) {
        if (pricing.isPaid() && capacity.isUnlimited()) {
            throw new IllegalArgumentException("유료 강의는 최대 수강인원이 있어야 합니다");
        }

        if (!pricing.isPaid() && !capacity.isUnlimited()) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원이 없어야 합니다");
        }
    }

    private EnrollmentPolicy createEnrollmentPolicy(SessionPricing pricing) {
        if (pricing.isPaid()) {
            return new PaidEnrollmentPolicy(pricing.fee());
        }
        return new FreeEnrollmentPolicy();
    }

    private boolean isOpen() {
        return status == SessionStatus.OPEN;
    }

}
