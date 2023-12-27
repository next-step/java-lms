package nextstep.courses.domain.course.session;

import java.util.Arrays;

public enum SessionType {
    FREE("무료"),
    CHARGE("유료");

    private final String description;

    SessionType(String description) {
        this.description = description;
    }

    public static SessionType find(String name) {
        return Arrays.stream(values())
                .filter(sessionType -> sessionType.name().equals(name))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("허용하는 값은 다음과 같습니다.\n %s", descriptions())
                        )
                );
    }

    public static String descriptions() {
        StringBuilder sb = new StringBuilder();
        for (SessionType sessionType : values()) {
            sb.append(sessionType.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    public boolean charged() {
        return this == CHARGE;
    }

    public boolean free() {
        return this == FREE;
    }
}
