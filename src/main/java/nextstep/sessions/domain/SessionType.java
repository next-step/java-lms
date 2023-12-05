package nextstep.sessions.domain;

import java.util.Arrays;

public enum SessionType {
    PAY("pay"),
    FREE("free"),
    ;
    private final String paymentType;

    SessionType(String paymentType) {
        this.paymentType = paymentType;
    }

    public static SessionType of(String sessionType) {
        return Arrays.stream(values())
                .filter(type -> type.paymentType.equals(sessionType))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("허용되지 않은 결제 타입입니다."));
    }
}
