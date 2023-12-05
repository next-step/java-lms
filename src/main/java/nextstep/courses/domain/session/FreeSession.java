package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.attendee.Attendee;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession implements Session {

    private final Long id;

    private final SessionInformation information;

    private final Image image;

    public FreeSession(Long id,
                       SessionInformation information,
                       Image image) {
        this.id = id;
        this.information = information;
        this.image = image;
    }

    @Override
    public Attendee enroll(Payment payment, NsUser nsUser) {
        information.validateApply();
        return new Attendee(nsUser.getId(), this.id);
    }
}
