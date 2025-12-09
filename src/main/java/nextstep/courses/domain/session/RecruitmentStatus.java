package nextstep.courses.domain.session;

import java.util.Arrays;

public enum RecruitmentStatus {
    NOT_RECRUITING("비모집중"),
    RECRUITING("모집중");

    private final String value;

    RecruitmentStatus(String value) {
        this.value = value;
    }

    public static RecruitmentStatus from(String description) {
        return Arrays.stream(values())
                .filter(status -> status.value.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 모집 상태입니다: " + description));
    }

    public boolean canEnroll() {
        return this == RECRUITING;
    }

    public String getValue() {
        return value;
    }
}
