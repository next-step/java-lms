package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionStatus {
    READY("READY"), ONGOING("ONGOING"), END("END");

    private final String name;

    private static final Map<String, SessionStatus> STATUS_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(SessionStatus::toString, Function.identity())));

    SessionStatus(String name) {
        this.name = name;
    }



    public static SessionStatus find(String status) {
        return STATUS_MAP.get(status);
    }


}
