package nextstep.courses.domain;

import java.util.Map;

public enum SessionPayment {
    FREE("무료"),
    PAID("유료");

    private final String status;
    private static final Map<String, SessionPayment> sessionPaymentMap = Map.of(FREE.getStatus(), FREE, PAID.getStatus(), PAID);

    SessionPayment(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static SessionPayment find(String payment) {
        return sessionPaymentMap.get(payment);
    }
}
