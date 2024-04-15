package nextstep.courses.domain.enums;

import java.util.Optional;

public enum SessionStatus {
    READY, IN_PROGRESS, END;

    public static Optional<SessionStatus> findByStatusStr(String sessionStr) {
        try {
            return Optional.of(SessionStatus.valueOf(sessionStr));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
