package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionType {
    FREE("FREE"), PAID("PAID");

    private final String name;

    private static final Map<String, SessionType> TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(SessionType::toString, Function.identity())));

    SessionType(String name) {
        this.name = name;
    }

    public static SessionType find(String status) {
        return TYPE_MAP.get(status);
    }
}
