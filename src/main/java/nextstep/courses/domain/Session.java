package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Optional;

/**
 * 강의를 나타내는 객체
 * 가변 객체입니다.
 */
public class Session {
    private SessionState state = SessionState.READY;
    private RegisteredUsers registeredUsers = new RegisteredUsers();

    public void registerUser(NsUser user) {
        registeredUsers.add(user);
    }
}
