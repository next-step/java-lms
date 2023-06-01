package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import nextstep.users.domain.NsUser;

public class Session {
    private SessionPeriod period;
    private ImageUrl imageUrl;
    private SessionStatus status;
    private SessionType type;
    private SessionUsers users;

        this.period = period;
        this.type = type;
        this.status = status;
    public Session(ImageUrl imageUrl, SessionPeriod period, SessionType type, SessionStatus status, int capacity) {
        this.imageUrl = Objects.requireNonNull(imageUrl);
        this.users = new SessionUsers(capacity);
    }

    public void register(NsUser user) {
        if (!status.isRecruiting()) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        users.add(user);
    }
}
