package nextstep.courses.domain.field;

import java.util.stream.Stream;

public enum SessionStatus {
    WAIT("wait"),
    OPEN("open"),
    CLOSED("closed"),
    ;

    private final String type;

    SessionStatus(String type) {
        this.type = type;
    }

    public static SessionStatus getType(String type) {
        return Stream.of(SessionStatus.values())
                     .filter(ss -> ss.getType().equals(type))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("정의되지 않은 강의 상태입니다"));
    }

    public String getType() {
        return type;
    }
}
