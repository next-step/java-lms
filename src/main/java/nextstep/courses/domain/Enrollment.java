package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private final Long sessionId;
    private final Long userId;
    private final LocalDateTime enrollmentDate;

    public Enrollment(Long sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.enrollmentDate = LocalDateTime.now();
    }
}
