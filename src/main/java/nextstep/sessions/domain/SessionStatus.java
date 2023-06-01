package nextstep.sessions.domain;

import nextstep.sessions.CannotRegisterException;

public enum SessionStatus {
    PREPARING, OPENING, CLOSED;

    public void validate() {
        if (this != OPENING) {
            throw new CannotRegisterException("모집중인 강의가 아닙니다.");
        }
    }
}
