package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatus {
    READY("준비"),
    OPEN("모집"),
    CLOSED("종료")
    ;

    private final String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public static SessionStatus find(String name) {
        return Arrays.stream(values())
                     .filter(v -> v.name().equals(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(name + "을 찾을 수 없습니다."));
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
