package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public enum SessionPayment {
    FREE("무료"),
    PAID("유료");

    private final String status;
    private static final Map<String, SessionPayment> sessionPaymentMap = new HashMap<>();

    static {
        for (SessionPayment sessionPayment : SessionPayment.values()) {
            sessionPaymentMap.put(sessionPayment.getStatus(), sessionPayment);
        }
    }

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
