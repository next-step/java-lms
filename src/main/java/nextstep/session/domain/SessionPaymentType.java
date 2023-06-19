package nextstep.session.domain;

import java.util.Arrays;

public enum SessionPaymentType {
    FREE, PAID;

    public static SessionPaymentType of(String paymentType) {
        return Arrays.stream(values())
                .filter(sessionPaymentType -> paymentType.equals(sessionPaymentType.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 지불 방법입니다."));
    }
}
