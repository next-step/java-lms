package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum SessionType {
    FREE("무료 강의"),
    PAID("유료 강의"),
    ETC("기타");

    private String description;

    SessionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static SessionType of(String sessionType) {
        return Arrays.stream(values())
                .filter(s -> s.toString().equals(sessionType))
                .findFirst()
                .orElse(ETC);
    }
}
