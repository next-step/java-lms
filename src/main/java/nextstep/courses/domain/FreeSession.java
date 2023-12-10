package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Image image, Period period) {
        super(image, period, SessionType.FREE);
    }
}
