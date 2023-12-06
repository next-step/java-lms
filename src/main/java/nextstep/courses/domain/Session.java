package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import static nextstep.courses.domain.SessionState.READY;

/**
 * 강의를 나타내는 객체
 * 가변 객체입니다.
 */
public class Session {
    private SessionState state = READY;
    private RegisteredUsers registeredUsers = new RegisteredUsers();
    private SessionImage coverImage;

    public Session(SessionState state, RegisteredUsers registeredUsers, SessionImage coverImage) {
        this.state = state;
        this.registeredUsers = registeredUsers;
        this.coverImage = coverImage;
    }

    public static Session createNewSession(SessionImage coverImage) {
        return new Session(READY, new RegisteredUsers(), coverImage);
    }

    public void registerUser(NsUser user) {
        registeredUsers.add(user);
    }
}
