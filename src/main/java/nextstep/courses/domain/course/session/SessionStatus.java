package nextstep.courses.domain.course.session;

import java.util.Arrays;

public enum SessionStatus {
    READY("준비중"),
    RECRUIT("모집중"),
    END("종료");

    private final String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public static SessionStatus find(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(name))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("허용하는 값은 다음과 같습니다.\n %s", descriptions())
                        )
                );
    }

    public static String descriptions() {
        StringBuilder sb = new StringBuilder();
        for (SessionStatus status : values()) {
            sb.append(status.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }
}
