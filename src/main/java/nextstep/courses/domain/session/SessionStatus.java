package nextstep.courses.domain.session;

import java.util.Arrays;

public enum SessionStatus {
    READY,
    RECRUIT,
    QUIT;

    public boolean isRecruit() {
        return RECRUIT == this;
    }
}
