package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionUserMapping {
    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private LocalDateTime createAt;

    public SessionUserMapping(Long id, Long sessionId, Long nsUserId, LocalDateTime createAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.createAt = createAt;
    }

    public void changeSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

}
