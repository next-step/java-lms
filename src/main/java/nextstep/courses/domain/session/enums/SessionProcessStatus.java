package nextstep.courses.domain.session.enums;

import nextstep.courses.domain.SessionStatus;

public enum SessionProcessStatus {
    WAITING("준비중"),
    PROCEEDING("진행중"),
    CLOSE("종료");

    private String name;

    SessionProcessStatus(String name) {
        this.name = name;
    }

}
