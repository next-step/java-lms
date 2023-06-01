package nextstep.courses.domain;

import java.util.Collections;
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
            throw new IllegalArgumentException("수강생이 가득 찼습니다: " + this.maxNumberOfUsers + "명");
        }
        if (this.registeredUsers.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
        this.registeredUsers.add(user);
    }

    private boolean isFull() {
        return countUsers() >= this.maxNumberOfUsers;
    }

    public int countUsers() {
        return this.registeredUsers.size();
    }

    public List<SessionUser> getRegisteredUsers() {
        return Collections.unmodifiableList(this.registeredUsers);
    }

    @Override
    public String toString() {
        return "SessionUsers{" +
                "maxNumberOfUsers=" + maxNumberOfUsers +
                ", registeredUsers=" + registeredUsers +
                '}';
    }

    public int getMaxUserCount() {
        return this.maxNumberOfUsers;
    }
}
