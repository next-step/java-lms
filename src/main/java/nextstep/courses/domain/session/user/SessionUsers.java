package nextstep.courses.domain.session.user;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.util.List;
import java.util.Optional;

public class SessionUsers {
    private List<SessionUser> sessionUsers;

    public static SessionUsers from(List<SessionUser> sessionUsers) {
        return new SessionUsers(sessionUsers);
    }

    private SessionUsers(List<SessionUser> sessionUsers) {
        this.sessionUsers = sessionUsers;
    }

    public void add(Long sessionId, Long userId) {
        this.sessionUsers.add(new SessionUser(sessionId, userId));
    }

    public boolean isSmallerThanMaxSize(int maxSize) {
        return this.sessionUsers.size() < maxSize;
    }

    public void addAll(List<NsUser> nsUserList, Long id) {
        NsUsers nsUsers = NsUsers.from(nsUserList);
        this.sessionUsers.addAll(nsUsers.convertSessionUsers(id));

    }

    public void accessSession(SessionUser sessionUser) {
        Optional<SessionUser> sessionUserResult = findBy(sessionUser);

        if (sessionUserResult.isPresent()) {
            sessionUserResult.get().accessSession();
        }
    }

    private Optional<SessionUser> findBy(SessionUser sessionUser) {
        return this.sessionUsers.stream()
                .filter(item -> item.equals(sessionUser))
                .findFirst();
    }

    public void cancelSession(SessionUser sessionUser) {
        Optional<SessionUser> sessionUserResult = findBy(sessionUser);
        if (sessionUserResult.isPresent()) {
            sessionUserResult.get().cancelSession();
        }
    }
}
