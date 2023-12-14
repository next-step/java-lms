package nextstep.courses.domain.session.enums;

import nextstep.courses.domain.SessionStatus;

import java.util.Arrays;

public enum SessionRecruitStatus {

    OPEN("모집중"),
    END("비모집중");

    private String name;

    SessionRecruitStatus(String name) {
        this.name = name;
    }

    public static boolean isOpen(SessionStatus status) {
        return status.OPEN.equals(status);
    }

    public static boolean isOpen(SessionRecruitStatus recruitStatus) {
        return OPEN.equals(recruitStatus);
    }

    public static SessionRecruitStatus by(SessionStatus status) {
        return Arrays.stream(SessionRecruitStatus.values())
                .filter(it -> it.name.equals(status.name()))
                .findAny()
                .orElse(SessionRecruitStatus.OPEN);
    }
}
