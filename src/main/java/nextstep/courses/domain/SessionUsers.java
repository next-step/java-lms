package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserException;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionUsers {
    private Set<NsUser> users;

    public SessionUsers() {
        users = new HashSet<>();
    }

    public SessionUsers(Set<NsUser> users) {
        this.users = new HashSet<>(users);
    }

    public void addUser(NsUser user) {
        validateDuplicateUser(user);
        users.add(user);
    }

    public void validateDuplicateUser(NsUser user) {
        if (users.contains(user)) {
            throw new SessionUserException("강의를 이미 신청한 유저이므로 중복으로 신청할 수 없습니다.");
        }
    }

    public int size() {
        return users.size();
    }

    public Set<NsUser> users() {
        return users;
    }
}
