package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private final SessionName name;
    private final SessionStatus status;

    public Session(String name, SessionStatus status) {
        this(SessionName.of(name), status);
    }

    public Session(SessionName name, SessionStatus status) {
        this.name = name;
        this.status = status;
    }

    public void apply(NsUser user) {
        validateStatus();
    }

    private void validateStatus() {
        if(!status.canRecruit()) {
            throw new IllegalArgumentException("강의는 모집중일 때 신청 가능합니다: " + status);
        }
    }
}
