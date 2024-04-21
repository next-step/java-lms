package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.courses.domain.session.impl.PaidSession;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.error.exception.NotExistSessionType;

public class SessionFactory {

    private SessionFactory() {
    }

    public static Session createSession(SessionEntity sessionEntity, Image image, SessionType sessionType) {
        if (sessionType == SessionType.PAID) {
            return new PaidSession(sessionEntity, image);
        } else if (sessionType == SessionType.FREE) {
            return new FreeSession(sessionEntity, image);
        }
        throw new NotExistSessionType(sessionType);
    }
}
