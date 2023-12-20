package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionType {
    FREE("무료"),
    CHARGE("유료");

    private String type;

    SessionType(String type) {
        this.type = type;
    }

    public static SessionType of(String type) {
        return Arrays.stream(values())
                .filter(it -> it.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 강의 타입니다."));
    }
}
