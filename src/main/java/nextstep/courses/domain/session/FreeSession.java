package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Long id, SessionBody sessionBody) {
        super(id, sessionBody);
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        validateSessionStatus();
        validateDuplicateEnrollment(nsUser);

        enrolledUsers.add(nsUser);
    }

}
