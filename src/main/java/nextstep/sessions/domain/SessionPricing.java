package nextstep.sessions.domain;

public class SessionPricing {

    private static final String ERROR_PAID_FEE = "유료 강의는 0원 초과 여야 합니다";
    private static final String ERROR_FREE_FEE = "무료 강의는 0원 이어야 합니다";

    private final boolean isPaid;
    private final int fee;

    SessionPricing(boolean isPaid, int fee) {
        validateFee(isPaid, fee);
        this.isPaid = isPaid;
        this.fee = fee;
    }

    public static SessionPricing paid(int fee) {
        return new SessionPricing(true, fee);
    }

    public static SessionPricing free() {
        return new SessionPricing(false, 0);
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int fee() {
        return fee;
    }

    private void validateFee(boolean isPaid, int fee) {
        if (isPaid && fee <= 0) {
            throw new IllegalArgumentException(ERROR_PAID_FEE);
        }
        if (!isPaid && fee != 0) {
            throw new IllegalArgumentException(ERROR_FREE_FEE);
        }
    }

}

