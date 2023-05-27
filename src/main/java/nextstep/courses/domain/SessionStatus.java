package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatus {
    READY,
    RECRUIT,
    QUIT;

    public boolean isRecruit() {
        return RECRUIT == this;
    }
}
