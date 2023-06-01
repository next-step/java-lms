package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Session {
    private final SessionPeriod period;
    private final SessionUsers users;
    private ImageUrl imageUrl;
    private SessionStatus status;
    private SessionType type;

    public Session(ImageUrl imageUrl, SessionPeriod period, SessionType type, SessionStatus status, int capacity) {
        this.imageUrl = Objects.requireNonNull(imageUrl);
        this.period = Objects.requireNonNull(period);
        this.type = Objects.requireNonNull(type);
        this.status = Objects.requireNonNull(status);
        this.users = new SessionUsers(capacity);
    }

    public void register(NsUser user) {
        if (!status.isRecruiting()) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        users.add(user);
    }
}
