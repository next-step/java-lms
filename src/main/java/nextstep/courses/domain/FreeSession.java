package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Set;

public class FreeSession extends Session {
    public FreeSession(Long id, SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate) {
        super(id, sessionImage, sessionStatus, sessionDate);
    }

    public FreeSession(Long id, SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate, Set<NsUser> students) {
        super(id, sessionImage, sessionStatus, sessionDate, students);
    }


    @Override
    protected void assertSatisfiedCondition(NsUser user, Payment payment) {
    }
}
