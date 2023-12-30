package nextstep.courses.domain;


public class SessionPayment {
    private final SessionPaymentType type;
    private final Long amount;

    public SessionPayment() {
        this(SessionPaymentType.FREE, 0L);
    }

    public SessionPayment(final SessionPayment sessionPayment){
        this(sessionPayment.type, sessionPayment.amount);
    }

    public SessionPayment(SessionPaymentType type, Long amount) {
        validatePrice(type, amount);
        this.type = type;
        this.amount = amount;
    }

    private void validatePrice(SessionPaymentType sessionPaymentType, Long price) {
        if (sessionPaymentType == SessionPaymentType.PAID && price <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.SESSION_PAYMENT_NEED_PRICE.getMessage());
        }
    }

    public String getTypeString() {
        return type.name();
    }

    public Long getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return type == SessionPaymentType.PAID;
    }
}
