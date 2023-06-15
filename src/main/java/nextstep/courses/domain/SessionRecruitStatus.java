package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionRecruitStatus {
    NOT_RECRUIT,
    RECRUIT;

    public static SessionRecruitStatus find(String recruitStatus) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(recruitStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(recruitStatus + "을 찾을 수 없습니다."));
    }

    public boolean isNotRecruiting() {
        return this != RECRUIT;
    }

}
