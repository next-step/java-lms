package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Long id,
                       SessionInformation information,
                       Image image) {
        super(id, information, image);
    }

    @Override
    public Attendee enroll(Payment payment, NsUser nsUser, Attendees attendees) {
        return checkAlreadyEnrolled(nsUser, attendees);
    }
}
