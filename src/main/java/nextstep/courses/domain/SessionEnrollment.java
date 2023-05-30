package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionEnrollment {
    ENROLLMENT("ENROLLMENT"),
    NON_ENROLLMENT("NON_ENROLLMENT");

    private final String name;

    private static final Map<String, SessionEnrollment> ENROLLMENT_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(SessionEnrollment::toString, Function.identity())));

    SessionEnrollment(String name) {
        this.name = name;
    }

    public static boolean isNotEnrollment(SessionEnrollment enrollment) {
        return ENROLLMENT != enrollment;
    }

    public static SessionEnrollment find(String status) {
        return ENROLLMENT_MAP.get(status);
    }
}
