package nextstep.courses.domain;

import java.util.Optional;

public enum SessionStatus {
    PREPARE,
    RECRUIT,
    END;

    public static SessionStatus findByName(String name) {
        return SessionStatus.valueOf(name.toUpperCase());
    }

    public boolean isRecruit() {
        return this.equals(RECRUIT);
    }


}
