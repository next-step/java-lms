package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionStatus {
    READY,
    OPEN,
    CLOSED;

    public static SessionStatus find(String sessionStatus) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(sessionStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(sessionStatus + "을 찾을 수 없습니다."));
    }

    public boolean isOpen() {
        return this == OPEN;
    }

    public boolean isClose() {
        return this == CLOSED;
    }

}
