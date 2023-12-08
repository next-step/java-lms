package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionTypeException;

import java.util.Arrays;

public enum SessionType {
    FREE("F"),
    PAID("P");

    private final String code;

    SessionType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static SessionType findByCode(String code) {
        return Arrays.stream(values())
                .filter(sessionType -> sessionType.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidSessionTypeException(code));
    }

    public boolean isPaid() {
        return this == PAID;
    }
}
