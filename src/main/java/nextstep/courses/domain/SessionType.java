package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionType {

    FREE("FREE"), PAY("PAY");

    private final String code;

    SessionType(String code) {
        this.code = code;
    }

    public static SessionType convert(String code) {
        return Arrays.stream(SessionType.values())
                .filter(s -> s.code.equals(code))
                .findFirst()
                .orElse(FREE);
    }
}
