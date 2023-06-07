package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionStatus {
    READY,
    OPEN,
    CLOSED;

    private static final Map<String, SessionStatus> SESSION_STATUS_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(SessionStatus::name, x -> x)));

    public static SessionStatus find(String name) {
        if (SESSION_STATUS_MAP.containsKey(name)) {
            return SESSION_STATUS_MAP.get(name);
        }
        throw new IllegalArgumentException(name + "을 찾을 수 없습니다.");
    }

    public boolean isOpen() {
        return this == OPEN;
    }

}
