package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    IN_PROGRESS("진행중"),
    ENDED("종료");

    private String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean canEnroll() {
        return this == RECRUITING;
    }

    public static SessionStatus of(String sessionStatus) {
        return Arrays.stream(values())
                .filter(s -> s.toString().equals(sessionStatus))
                .findFirst()
                .orElse(null);
    }
}
