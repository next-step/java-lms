package nextstep.courses.domain.session.constant;

import java.util.Arrays;

public enum SessionType {
    FREE, PAID;

    public static SessionType from(String value) {
        return Arrays.stream(SessionType.values())
                .filter(type -> type.matchType(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("강의는 무료 강의와 유료 강의만 개설 가능합니다."));
    }

    private boolean matchType(String value) {
        return this.name().equals(value);
    }
}
