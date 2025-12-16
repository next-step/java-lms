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

    public Session(Long id, LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity,
                   boolean unlimited, int fee, int enrollCount,
                   SessionImage image) {
        this(id, new SessionInfo(new Period(startDate, endDate), image),
                new SessionPricing(isPaid, fee), new Capacity(maxCapacity, unlimited, enrollCount));
    }

    public Session(Long id, SessionInfo sessionInfo, SessionPricing pricing, Capacity capacity) {
        validatePricingAndCapacity(pricing, capacity);
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.status = SessionStatus.PREPARING;
        this.pricing = pricing;
        this.capacity = capacity;
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
        this.capacity = capacity.increaseEnrollCount();
    }

    private void validateOpen() {
        if (!isOpen()) {
            throw new IllegalStateException(ERROR_SESSION_NOT_OPEN);
        }
    }

    private void validateCapacity() {
        if (capacity.isFull()) {
            throw new IllegalStateException(Session.ERROR_CAPACITY_EXCEEDED);
        }
    }

    private void validatePaymentAmount(Enrollment enrollment) {
        if (pricing.isPaid() && !enrollment.payment().isPaidFor(pricing.fee())) {
            throw new IllegalArgumentException(ERROR_PAYMENT_AMOUNT_MISMATCH);
        }
    }

    private void validatePricingAndCapacity(SessionPricing pricing, Capacity capacity) {
        if (pricing.isPaid() && capacity.isUnlimited()) {
            throw new IllegalArgumentException("유료 강의는 최대 수강인원이 있어야 합니다");
        }
        if (!pricing.isPaid() && !capacity.isUnlimited()) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원이 없어야 합니다");
        }
    }

    private boolean isOpen() {
        return status == SessionStatus.OPEN;
    }

}
