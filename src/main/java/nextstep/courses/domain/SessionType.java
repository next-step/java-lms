package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionTypeException;

import java.util.Arrays;

public enum SessionType {
    FREE,
    PAID;

    public static SessionType findByCode(String code) {
        return Arrays.stream(values())
                .filter(sessionType -> sessionType.name().equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidSessionTypeException(code));
    }

    public boolean isPaid() {
        return this == PAID;
    }
}
