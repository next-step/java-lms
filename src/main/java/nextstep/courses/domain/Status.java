package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Status {
    READY("READY"), RECRUIT("RECRUIT"), END("END");

    private final String name;

    private static final Map<String, Status> STATUS_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Status::toString, Function.identity())));

    Status(String name) {
        this.name = name;
    }

    public static boolean isNotRecruit(Status status) {
        return RECRUIT != status;
    }

    public static Status find(String status) {
        if (STATUS_MAP.containsKey(status)) {
            return STATUS_MAP.get(status);
        }
        return null;
    }


}
