package nextstep.courses.dto;

import nextstep.courses.domain.SessionPaymentType;

public class SessionPaymentDTO {
    private final SessionPaymentType type;
    private final Long amount;

    public SessionPaymentDTO(SessionPaymentType type, Long amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getTypeString() {
        return type.name();
    }

    public Long getAmount() {
        return amount;
    }
}
