package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidSession extends Session {
    private Capacity maxCapacity;
    private TuitionFee tuitionFee;

    public PaidSession(Long id, String name, Period period, Image coverImage,
                       SessionStatus status, int maxCapacity, int tuitionFee) {
        super(id, name, period, coverImage, status);
        this.maxCapacity = new Capacity(maxCapacity);
        this.tuitionFee = new TuitionFee(tuitionFee);
    }

    public Capacity getCapacity() {
        return maxCapacity;
    }

    public TuitionFee getTuitionFee() {
        return tuitionFee;
    }

    @Override
    protected void validateRegistration(Long studentId, Payment payment) {
        if (registeredStudents.size() >= maxCapacity.getValue()) {
            throw new IllegalStateException("수강 인원을 초과하였습니다.");
        }
        if (payment == null) {
            throw new IllegalArgumentException("결제 정보가 없습니다.");
        }
        if (!this.id.equals(payment.getSessionId())) {
            throw new IllegalArgumentException("결제한 강의와 일치하지 않습니다.");
        }
        if (!studentId.equals(payment.getNsUserId())) {
            throw new IllegalArgumentException("결제한 사용자와 일치하지 않습니다.");
        }
        if (!this.tuitionFee.isSameAmount(payment.getAmount())) {
            throw new IllegalArgumentException("결제 금액과 일치하지 않습니다.");
        }
    }

    @Override
    public SessionType getType() {
        return SessionType.PAID;
    }
}