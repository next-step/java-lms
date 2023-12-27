package nextstep.courses.domain.course.session;

import java.util.Arrays;

public enum SessionProgressStatus {
    READY("준비중"),
    ONGOING("진행중"),
    END("종료");

    private final String description;

    SessionProgressStatus(String description) {
        this.description = description;
    }

    public static SessionProgressStatus find(String name) {
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
        for (SessionProgressStatus status : values()) {
            sb.append(status.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    public boolean notReadyOrOnGoing() {
        return this == SessionProgressStatus.END;
    }
}
