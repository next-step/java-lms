package nextstep.courses.domain.session;

import nextstep.courses.SessionNotFoundException;

import java.util.Arrays;

public enum SessionRecruitmentType {
    RECRUITING, NOT_RECRUITING;

    public static SessionRecruitmentType findBy(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new SessionNotFoundException("모집 상태 값에 대한 마이그레이션이 진행되지 않았습니다."));
    }
}
