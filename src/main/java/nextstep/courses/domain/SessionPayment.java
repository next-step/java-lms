package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionPayment {
    FREE("무료"),
    PAID("유료");

    private final String status;

    SessionPayment(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static SessionPayment find(String payment) {
        return Arrays.stream(values())
                .filter(x -> x.status == payment)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(payment + "에 해당하는 값이 없습니다."));
    }
}
