package nextstep.payments.domain;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("대기중"),
    APPROVED("승인 완료"),
    CANCELED("승인 취소");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public static PaymentStatus fromString(String status) {
        for (PaymentStatus s : values()) {
            if (s.status.equalsIgnoreCase(status)) {
                return s;
            }
        }
        return PENDING;
    }
}
