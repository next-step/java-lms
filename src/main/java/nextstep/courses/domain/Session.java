package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;

import static nextstep.courses.domain.SessionState.READY;

/**
 * 강의를 나타내는 객체
 * 가변 객체입니다.
 */
public abstract class Session {
    private SessionState state;
    private final RegisteredUsers registeredUsers;
    private SessionImage coverImage;
    private SessionDuration duration;

    protected Session() {
        this(
                READY,
                new RegisteredUsers(),
                null,
                null
        );
    }

    protected Session(SessionState state, RegisteredUsers registeredUsers, SessionImage coverImage, SessionDuration duration) {
        this.state = state;
        this.registeredUsers = registeredUsers;
        this.coverImage = coverImage;
        this.duration = duration;
    }

    public void registerUser(NsUser user) {
        if (this.state != READY) {
            throw new IllegalStateException("사용자 등록은 모집중 상태에서만 가능하지만 모집중 상태가 아닙니다.");
        }
        registeredUsers.add(user);
    }

    public void updateStateTo(SessionState state) {
        if (state == null) {
            throw new IllegalArgumentException("null로 상태를 업데이트할 수 없습니다.");
        }

        this.state = state;
    }

    protected boolean isTheNumberOfRegisteredUserLessThan(int maxUserCount) {
        return this.registeredUsers.theNumberOfUsers() < maxUserCount;
    }
}