package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionType {
    FREE, CHARGED;

    public static SessionType find(String sessionType) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(sessionType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의 타입 입니다."));
    }
}
