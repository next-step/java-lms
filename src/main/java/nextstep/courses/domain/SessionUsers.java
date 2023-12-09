package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserException;
import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class SessionUsers {
    private Set<SessionUser> sessionUsers;

    public SessionUsers() {
        sessionUsers = new HashSet<>();
    }

    public SessionUsers(Set<SessionUser> users) {
        this.sessionUsers = new HashSet<>(users);
    }

    public SessionUser addUser(long sessionId, SelectionStatus selectionStatus, NsUser user) {
        SessionUser sessionUser = new SessionUser(sessionId, user.getId(), selectionStatus);
        validateDuplicateUser(sessionUser);
        sessionUsers.add(sessionUser);
        return sessionUser;
    }

    public void validateDuplicateUser(SessionUser sessionUser) {
        if (sessionUsers.contains(sessionUser)) {
            throw new SessionUserException("강의를 이미 신청한 유저이므로 중복으로 신청할 수 없습니다.");
        }
    }

    public int size() {
        return sessionUsers.size();
    }

    public Set<SessionUser> users() {
        return Collections.unmodifiableSet(sessionUsers);
    }

    public SessionUser approve(NsUser user) {
        return modifyUser(user, SessionUser::approve);
    }

    public SessionUser cancel(NsUser user) {
        return modifyUser(user, SessionUser::cancel);
    }

    private SessionUser modifyUser(NsUser user, Function<SessionUser, SessionUser> operation) {
        return sessionUsers.stream()
                .filter(sessionUser -> sessionUser.isSameUser(user))
                .map(operation)
                .findFirst()
                .orElseThrow(() -> new SessionUserException("강의를 신청 하지 않은 유저입니다."));
    }

}
