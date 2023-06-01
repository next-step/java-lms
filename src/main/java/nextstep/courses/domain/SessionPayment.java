package nextstep.courses.domain;

import java.util.Map;

public enum SessionPayment {
    FREE("무료"),
    PAID("유료");

    private final String status;
    private static final Map<String, SessionPayment> sessionPaymentMap = Map.of(FREE.name(), FREE, PAID.name(), PAID);

    SessionPayment(String status) {
        this.status = status;
    }

    public static SessionPayment find(String payment) {
        return sessionPaymentMap.get(payment);
    }
}
