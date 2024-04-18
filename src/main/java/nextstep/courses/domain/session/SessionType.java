package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionTypeInvalidException;

import java.util.Arrays;

public enum SessionType {

    FREE("무료"),
    PAID("유료");

    private final String type;

    SessionType(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }

    public static SessionType convert(String type) {
        return Arrays.stream(values())
                .filter(sessionType -> sessionType.type.equals(type))
                .findAny()
                .orElseThrow(() -> new SessionTypeInvalidException(type));
    }

}
