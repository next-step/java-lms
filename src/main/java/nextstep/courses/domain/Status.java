package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionStatusException;

import java.util.Arrays;

public enum Status {
    NOT_OPEN,
    OPEN,
    CLOSED;


    public Status ofOpen() {
        return OPEN;
    }

    public boolean isOpen() {
        return this == OPEN;
    }

    public static Status findByName(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new InvalidSessionStatusException(name));
    }
}
