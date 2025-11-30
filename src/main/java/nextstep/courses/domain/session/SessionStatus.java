package nextstep.courses.domain.session;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    CLOSED("종료");

    private final String value;

    SessionStatus(String value) {
        this.value = value;
    }

    public static SessionStatus from(String value) {
        return Arrays.stream(values())
                .filter(status -> status.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 강의 상태입니다: " + value));
    }

    public boolean canEnroll() {
        return this == RECRUITING;
    }
}
