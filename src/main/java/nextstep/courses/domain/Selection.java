package nextstep.courses.domain;

public class Selection {
    private final Long id;
    private final Long userId;
    private final Long sessionId;
    private boolean hasPaid = false;

    public static Selection createNewInstance(Long userId, Long sessionId) {
        return new Selection(0L, userId, sessionId, false);
    }

    public static Selection createFromData(Long id, Long userId, Long sessionId, boolean hasPaid) {
        return new Selection(id, userId, sessionId, hasPaid);
    }

    private Selection(Long id, Long userId, Long sessionId, boolean hasPaid) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.hasPaid = hasPaid;
    }

    public boolean hasPaid() {
        return hasPaid;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

}
