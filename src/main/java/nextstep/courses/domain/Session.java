package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import nextstep.users.domain.NsUser;

public class Session {
    private ImageInfo imageInfo;
    private SessionPeriod period;
    private SessionStatus status;
    private SessionType type;
    private SessionUsers users;

    public Session(ImageInfo imageInfo, SessionPeriod period, SessionType type, SessionStatus status, int capacity) {
        this.imageInfo = imageInfo;
        this.period = period;
        this.type = type;
        this.status = status;
        this.users = new SessionUsers(capacity);
    }

    public void register(NsUser user) {
        if (!status.isRecruiting()) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        users.add(user);
    }
}
