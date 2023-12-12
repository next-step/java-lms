package nextstep.courses.domain.session.enums;

import nextstep.courses.domain.SessionStatus;

import java.util.Arrays;

public enum SessionProcessStatus {
    WAITING("준비중"),
    PROCEEDING("진행중"),
    CLOSE("종료");

    private String name;

    SessionProcessStatus(String name) {
        this.name = name;
    }

    public static SessionProcessStatus by(SessionStatus status) {
        return Arrays.stream(SessionProcessStatus.values())
                .filter(it -> it.name.equals(status.name()))
                .findAny()
                .orElse(SessionProcessStatus.PROCEEDING);
    }
}
