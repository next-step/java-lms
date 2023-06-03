package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionType {

    FREE("무료"),
    CHANGED("유료")
    ;

    private String name;

    public static SessionType find(String sessionType) {
        return Arrays.stream(values())
                .filter(type -> type.name.equalsIgnoreCase(sessionType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의 타입입니다."));
    }
    SessionType(String name) {
        this.name = name;
    }
}
