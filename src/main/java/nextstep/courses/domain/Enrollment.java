package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Enrollment {
    private final NsUser nsUser;
    private final long sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(NsUser nsUser, long sessionId) {
        this(nsUser, sessionId, LocalDateTime.now(), null);
    }

    public Enrollment(NsUser nsUser, long sessionId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.nsUser = nsUser;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
