package nextstep.courses.domain.session;

public class Enrollment {
    private final Long id;
    private final Long sessionId;
    private final Long userId;
    private boolean isAllowedToEnroll;

    private Enrollment(Long id, Long sessionId, Long userId, boolean isAllowedToEnroll) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.isAllowedToEnroll = isAllowedToEnroll;
    }

    public static final Enrollment of(Long id, Long sessionId, Long userId, boolean isAllowedToEnroll) {
        return new Enrollment(id, sessionId, userId, isAllowedToEnroll);
    }

    public Long getId() { return id; }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isAllowedToEnroll() { return isAllowedToEnroll; }

    public void allowToEnroll() {
        this.isAllowedToEnroll = true;
    }

    public void cancelEnrollment() {
        this.isAllowedToEnroll = false;
    }

}
