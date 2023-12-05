package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.attendee.Attendee;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession extends Session {

    private final TotalAttendee totalAttendee;

    private final Price amount;

    public PaidSession(Long id,
                       SessionInformation information,
                       Image image,
                       TotalAttendee totalAttendee,
                       Price amount) {
        super(id, information, image);
        this.totalAttendee = totalAttendee;
        this.amount = amount;
    }

    @Override
    public Attendee enroll(Payment payment, NsUser nsUser) {
        amount.validatePrice(payment);
        Session paidSession = new PaidSession(this.id,
                                              this.information,
                                              this.image,
                                              totalAttendee.add(),
                                              this.amount);
        return new Attendee(nsUser.getId(), paidSession.id);
    }
}
