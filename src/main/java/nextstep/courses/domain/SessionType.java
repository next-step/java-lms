package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionType {

    FREE, PAID;

    public static SessionType of(String type) {
        return Arrays.stream(SessionType.values())
                .filter(sessionType -> sessionType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 강의 유형이 없습니다."));
    }
}
