package nextstep.courses.domain.sessionuser;

public class SessionUser {

    private Long userId;
    private Long sessionId;
    private UserType userType;
    private boolean isCanceled;

    public SessionUser(Long userId, Long sessionId, UserType userType, boolean isCanceled) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.userType = userType;
        this.isCanceled = isCanceled;
    }

    public SessionUser(Long userId, Long sessionId, UserType userType) {
        this(userId, sessionId, userType, false);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public UserType userType() {
        return userType;
    }

    public String userTypeName() {
        return userType.name();
    }


}
