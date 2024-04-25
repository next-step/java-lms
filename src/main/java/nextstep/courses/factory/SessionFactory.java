package nextstep.courses.factory;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.error.exception.NotExistSessionType;

public class SessionFactory {

    private SessionFactory() {
    }

    public static Session get(SessionEntity sessionEntity) {
        if (FeeType.PAID == FeeType.valueOf(sessionEntity.getFeeType())) {
            return new PaidSession(sessionEntity);
        }

        if (FeeType.FREE == FeeType.valueOf(sessionEntity.getFeeType())) {
            return new FreeSession(sessionEntity);
        }

        throw new NotExistSessionType();
    }
}
