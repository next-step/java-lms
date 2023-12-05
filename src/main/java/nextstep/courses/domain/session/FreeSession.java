package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.attendee.Attendee;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Long id,
                       SessionInformation information,
                       Image image) {
        super(id, information, image);
    }

    @Override
    public Attendee enroll(Payment payment, NsUser nsUser) {
        return new Attendee(nsUser.getId(), this.id);
    }
}
