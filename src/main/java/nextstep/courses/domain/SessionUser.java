package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserException;
import nextstep.users.domain.NsUser;

import java.util.Objects;

import static nextstep.courses.domain.SessionUserStatus.APPROVE;
import static nextstep.courses.domain.SessionUserStatus.CANCEL;

public class SessionUser {

    private final Long sessionId;
    private final Long userId;
    private final SelectionStatus selectionStatus;
    private final SessionUserStatus sessionUserStatus;

    public SessionUser(Long sessionId, Long userId, SelectionStatus selectionStatus) {
        this(sessionId, userId, selectionStatus, SessionUserStatus.WAITING);
    }

    public SessionUser(Long sessionId, Long userId, SelectionStatus selectionStatus, SessionUserStatus sessionUserStatus) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.selectionStatus = selectionStatus;
        this.sessionUserStatus = sessionUserStatus;
    }

    public boolean isSameUser(NsUser user){
        return user.matchId(userId);
    }

    public SessionUser approve(){
        validateSelectedUser();
        return new SessionUser(sessionId, userId, selectionStatus, APPROVE);
    }

    private void validateSelectedUser() {
        if(!selectionStatus.isSelected()){
            throw new SessionUserException("선발 되지 않은 유저는 수강 승인 할 수 없습니다.");
        }
    }

    public SessionUser cancel(){
        return new SessionUser(sessionId, userId, selectionStatus, CANCEL);
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long userId() {
        return userId;
    }

    public SelectionStatus selectionStatus() {
        return selectionStatus;
    }

    public SessionUserStatus sessionUserStatus() {
        return sessionUserStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionUser that = (SessionUser) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId);
    }
}
