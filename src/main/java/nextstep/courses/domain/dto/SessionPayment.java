package nextstep.courses.domain.dto;

import nextstep.courses.domain.SessionPaymentType;

public class SessionPayment {
    private final SessionPaymentType type;
    private final Long amount;

    public SessionPayment(){
        this(SessionPaymentType.FREE, 0L);
    }


    public SessionPayment(SessionPaymentType type, Long amount) {
        this.type = type;
        this.amount = amount;
    }

    public boolean isPaid(){
        return type == SessionPaymentType.PAID;
    }

    public SessionPaymentType getType() {
        return type;
    }

    public Long getAmount() {
        return amount;
    }
}
