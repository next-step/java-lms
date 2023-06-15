package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionJoinStatus {
    APPLICATION,
    APPROVAL,
    REJECTION;


    public static SessionJoinStatus find(String joinStatus) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(joinStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(joinStatus + "을 찾을 수 없습니다."));
    }

}
