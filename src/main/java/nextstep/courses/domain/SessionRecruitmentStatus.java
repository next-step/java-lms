package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionRecruitmentStatus {
    RECRUITING, NON_RECRUITING;

    public static SessionRecruitmentStatus find(String sessionRecruitmentStatus) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(sessionRecruitmentStatus))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의 상태입니다."));
    }

    public boolean canJoin() {
        return this.equals(RECRUITING);
    }
}
