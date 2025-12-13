package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

    private final Period period;

    private SessionStatus status;

    private final SessionPricing pricing;

    private Capacity capacity;

    private SessionImage image;

    Session(LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity, int fee, int enrollCount,
            SessionImage image) {
        this(startDate, endDate, isPaid, maxCapacity, fee, image);
    }

    public Session(LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity, int fee,
                   SessionImage image) {
        this(new Period(startDate, endDate), new SessionPricing(isPaid, fee), new Capacity(maxCapacity), image);
    }

    public Session(Period period, SessionPricing pricing, Capacity capacity,
                   SessionImage image) {
        validatePricingAndCapacity(pricing, capacity);
        validateImage(image);
        this.period = period;
        this.status = SessionStatus.PREPARING;
        this.pricing = pricing;
        this.capacity = capacity;
        this.image = image;
    }

    public SessionStatus status() {
        return status;
    }

    public boolean canEnroll() {
        if (!capacity.canEnroll()) {
            return false;
        }
        return status == SessionStatus.OPEN;
    }

    public void startRecruiting() {
        this.status = SessionStatus.OPEN;
    }

    public void enroll() {
        if (!canEnroll()) {
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

    private static void validateImage(SessionImage image) {
        if (image == null) {
            throw new IllegalArgumentException("강의 커버 이미지는 필수입니다.");
        }
    }

}
