package nextstep.courses.domain.field;

import java.util.stream.Stream;

public enum SessionType {
    FREE("free"),
    PAID("paid"),
    ;

    private final String type;

    SessionType(String type) {
        this.type = type;
    }

    public static SessionType getType(String type) {
        return Stream.of(SessionType.values())
                     .filter(sessionType -> sessionType.getType().equals(type))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("잘 못된 강의 구분입니다"));
    }

    public String getType() {
        return type;
    }
}
