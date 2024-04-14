package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {
    public FreeSession(Long id, SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate) {
        super(id, sessionImage, sessionStatus, sessionDate);
    }

    @Override
    protected void assertSatisfiedCondition(NsUser user, Payment payment) {
    }
}
