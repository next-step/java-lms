package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Long id, SessionBody sessionBody, SessionEnrollment sessionEnrollment) {
        super(id, sessionBody, sessionEnrollment);
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        validateSessionStatus();
        validateDuplicateEnrollment(nsUser);

        sessionEnrollment.enrollUser(nsUser);
    }

}
