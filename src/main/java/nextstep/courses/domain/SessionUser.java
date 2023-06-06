package nextstep.courses.domain;

import java.util.Objects;

public class SessionUser {
    private final Long userId;
    private SessionUserStatus sessionUserStatus;

    public SessionUser(Long userId) {
        this(userId, SessionUserStatus.WAIT);
    }

    public SessionUser(Long userId, SessionUserStatus sessionUserStatus) {
        this.userId = userId;
        this.sessionUserStatus = sessionUserStatus;
    }

    public void approve() {
        this.sessionUserStatus = SessionUserStatus.APPROVAL;
    }

    public void reject() {
        this.sessionUserStatus = SessionUserStatus.REJECT;
    }

    public boolean isApproved() {
        return this.sessionUserStatus.isApproved();
    }

    public Long getUserId() {
        return this.userId;
    }

    public SessionUserStatus getSessionUserStatus() {
        return this.sessionUserStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionUser that = (SessionUser) o;

        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
