package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.util.Objects;

public class SessionFee {
    private static final String INVALID_FEE_MESSAGE = "강의 금액을 확인해주세요.";

    private final Long fee;

    public SessionFee(final Long fee) {
        if (fee < 0) {
            throw new IllegalArgumentException(INVALID_FEE_MESSAGE);
        }
        this.fee = fee;
    }

    public boolean isFree() {
        return fee == 0;
    }

    public static SessionFee ZERO() {
        return new SessionFee(0L);
    }

    public static SessionFee of(Long fee) {
        return new SessionFee(fee);
    }

    public boolean isNotEqualTo(final Payment payment) {
        return !fee.equals(payment.getSessionFee());
    }

    public Long getFee() {
        return fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionFee that = (SessionFee) o;
        return Objects.equals(fee, that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fee);
    }
}
