package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatusType {
    PREPARING,
    RECRUITING,
    CLOSE;

    public boolean canRegister() {
        return this == RECRUITING;
    }

    public static SessionStatusType find(String name) {
        return Arrays.stream(SessionStatusType.values())
                .filter(sessionStatusType -> sessionStatusType.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의 상태 입니다."));
    }
}
