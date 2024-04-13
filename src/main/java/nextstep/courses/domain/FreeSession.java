package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {
    public FreeSession(Long id, SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate) {
        super(id, sessionImage, sessionStatus, sessionDate);
    }

    @Override
    protected void assertRecruit(NsUser user) {
        if (!getSessionStatus().isRecruit() || getStudents().contains(user)) {
            throw new NotRecruitException();
        }
    }

    @Override
    protected Payment payResult(NsUser user) {
        return new Payment(null, getId(), user.getId(), 0L);
    }
}
