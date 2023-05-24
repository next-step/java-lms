package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
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

    public static SessionStatus of(String description) {
        return Arrays.stream(values())
                .filter(sessionStatus -> sessionStatus.getDescription().equals(description.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 상태입니다."));
    }

}
