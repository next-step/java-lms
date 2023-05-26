package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionRecruitStatus {
    NOT_RECRUIT("비모집"), RECRUIT("모집"), NONE("없음");

    private final String description;

    SessionRecruitStatus(String description) {
        this.description = description;
    }

    private static final Map<String, SessionRecruitStatus> SESSION_RECRUIT_STATUS_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(SessionRecruitStatus::name, x -> x)));

    public static SessionRecruitStatus find(String name) {
        if (SESSION_RECRUIT_STATUS_MAP.containsKey(name)) {
            return SESSION_RECRUIT_STATUS_MAP.get(name);
        }

        return NONE;
    }

    public boolean isNotRecruiting() {
        return this == NOT_RECRUIT;
    }
}
