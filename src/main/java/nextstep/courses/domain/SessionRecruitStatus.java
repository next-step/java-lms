package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionRecruitStatus {
    NOT_RECRUIT,
    RECRUIT;

    private static final Map<String, SessionRecruitStatus> SESSION_RECRUIT_STATUS_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(SessionRecruitStatus::name, x -> x)));

    public static SessionRecruitStatus find(String name) {
        if (SESSION_RECRUIT_STATUS_MAP.containsKey(name)) {
            return SESSION_RECRUIT_STATUS_MAP.get(name);
        }

        throw new IllegalArgumentException(name + "을 찾을 수 없습니다.");
    }

    public boolean isNotRecruiting() {
        return this != RECRUIT;
    }

}
