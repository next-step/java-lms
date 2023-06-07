package nextstep.sessions.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum SessionRecruitmentStatus {
    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");

    private static final Map<String, SessionRecruitmentStatus> VALUE_MAP
            = Arrays.stream(SessionRecruitmentStatus.values())
            .collect(Collectors.toUnmodifiableMap(SessionRecruitmentStatus::getName, o -> o));

    private final String name;

    SessionRecruitmentStatus(String name) {
        this.name = name;
    }

    public static SessionRecruitmentStatus find(String name) {
        if (VALUE_MAP.containsKey(name)) {
            return VALUE_MAP.get(name);
        }
        throw new IllegalArgumentException("유효하지 않은 강의 모집상태입니다.(" + name + ")");
    }

    public String getName() {
        return name;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }

}
