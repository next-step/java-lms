package nextstep.courses.domain;

import java.util.List;

public class SessionUsers {
    private final int maxNumberOfUsers;
    private final List<SessionUser> registeredUsers;

    public SessionUsers(int maxNumberOfUsers, List<SessionUser> registeredUsers) {
        this.maxNumberOfUsers = maxNumberOfUsers;
        this.registeredUsers = registeredUsers;
    }

    public void registerUser(SessionUser user) {
        if (isFull()) {
            throw new IllegalArgumentException("이미 등록된 강의입니다.");
        }
        if (this.registeredUsers.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
        this.registeredUsers.add(user);
    }

    public void registerUser(Long userId) {
        registerUser(new SessionUser(userId));
    }

    private boolean isFull() {
        return countUsers() >= this.maxNumberOfUsers;
    }

    public int countUsers() {
        return this.registeredUsers.size();
    }
}
