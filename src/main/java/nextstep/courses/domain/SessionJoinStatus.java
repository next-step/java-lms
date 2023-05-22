package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionJoinStatus {
    APPLICATION("신청"), APPROVAL("승인"), REJECTION("거절");

    private final String description;

    SessionJoinStatus(String description) {
        this.description = description;
    }

    private static final Map<String, SessionJoinStatus> SESSION_JOIN_STATUS_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(SessionJoinStatus::name, x -> x)));

    public static SessionJoinStatus find(String name) {
        if (SESSION_JOIN_STATUS_MAP.containsKey(name)) {
            return SESSION_JOIN_STATUS_MAP.get(name);
        }

        throw new IllegalArgumentException(name + "을 찾을 수 없습니다.");
    }

}
