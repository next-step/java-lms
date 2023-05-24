package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum SessionType {
    FREE("무료 강의"),
    PAID("유료 강의");

    private String description;

    SessionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static SessionType of(String description) {
        return Arrays.stream(values())
                .filter(sessionType -> sessionType.getDescription().equals(description.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 형식입니다."));
    }
}
