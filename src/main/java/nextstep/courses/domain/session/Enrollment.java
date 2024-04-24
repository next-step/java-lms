package nextstep.courses.domain.session;

public class Enrollment {
    private final Long id;
    private final Long sessionId;
    private final Long userId;
    private boolean hasPaid;

    private Enrollment(Long id, Long sessionId, Long userId, boolean hasPaid) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.hasPaid = hasPaid;
    }

    public static final Enrollment of(Long id, Long sessionId, Long userId, boolean hasPaid) {
        return new Enrollment(id, sessionId, userId, hasPaid);
    }

    public Long getId() { return id; }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean hasPaid() {
        return hasPaid;
    }
}