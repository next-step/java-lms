package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.ZonedDateTime;

import static nextstep.courses.domain.SessionState.READY;

/**
 * 강의를 나타내는 객체
 * 가변 객체입니다.
 */
public class Session {
    private SessionState state = READY;
    private RegisteredUsers registeredUsers = new RegisteredUsers();
    private SessionImage coverImage;
    private SessionDuration duration;

    public Session(SessionState state, RegisteredUsers registeredUsers, SessionImage coverImage, SessionDuration duration) {
        this.state = state;
        this.registeredUsers = registeredUsers;
        this.coverImage = coverImage;
        this.duration = duration;
    }

    public static Session createNewSession(SessionImage coverImage, SessionDuration duration) {
        return new Session(READY, new RegisteredUsers(), coverImage, duration);
    }

    public void registerUser(NsUser user) {
        registeredUsers.add(user);
    }
}
