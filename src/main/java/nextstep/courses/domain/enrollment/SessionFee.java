package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;

public class SessionFee {

    public static final Long FREE = 0L;

    private final Long fee;

    public SessionFee(Long fee) {
        this.fee = fee;
    }

    public boolean differentFrom(Payment payment) {
        return !fee.equals(payment.getAmount());
    }

    public Long get() {
        return fee;
    }
}
