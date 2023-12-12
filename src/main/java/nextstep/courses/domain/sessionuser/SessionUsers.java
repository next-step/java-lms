package nextstep.courses.domain.sessionuser;

import nextstep.courses.CanNotEnrollSameNsUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionUsers {

    private List<SessionUser> sessionUsers = new ArrayList<>();

    public SessionUsers() {
    }

    public SessionUsers(List<SessionUser> sessionUsers) {
        this.sessionUsers = sessionUsers;
    }

    public long notCanceledUserSize() {
        return sessionUsers.stream().filter(el -> !el.isCanceled())
                .count();
    }

    public void add(SessionUser sessionUser) {
        Optional<SessionUser> savedSessionUser = sessionUsers.stream().filter(el -> el.getUserId() == sessionUser.getUserId())
                .findFirst();

        if (savedSessionUser.isPresent()) {
            throw new CanNotEnrollSameNsUserException("동일한 사람이 2번 신청할 수 없습니다.");
        }
        sessionUsers.add(sessionUser);
    }

    public SessionUser findSessionUser(Long userId) {
        return sessionUsers.stream().filter(el -> el.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));
    }
}
