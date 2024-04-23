package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.courses.domain.session.impl.PaidSession;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.error.exception.NotExistSessionType;

public class SessionFactory {

    private SessionFactory() {
    }

    public static Session createSession(SessionEntity sessionEntity) {
        if (sessionEntity.isPaidType()) {
            return new PaidSession(sessionEntity);
        }

        if (sessionEntity.isFreeType()) {
            return new FreeSession(sessionEntity);
        }

        throw new NotExistSessionType();
    }
}
