package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Long id, String title, SessionPeriod period, CoverImage coverImage) {
        super(id, title, period, coverImage);
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        validateSessionStatus();
        validateDuplicateEnrollment(nsUser);

        enrolledUsers.add(nsUser);
    }

}
