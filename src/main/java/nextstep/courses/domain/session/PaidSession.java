package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.attendee.Attendee;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession implements Session {

    private final Long id;

    private final SessionInformation information;

    private final Image image;

    private final TotalAttendee totalAttendee;

    private final Price amount;

    public PaidSession(Long id,
                       SessionInformation information,
                       Image image,
                       TotalAttendee totalAttendee,
                       Price amount) {
        this.id = id;
        this.information = information;
        this.image = image;
        this.totalAttendee = totalAttendee;
        this.amount = amount;
    }

    @Override
    public Attendee enroll(Payment payment, NsUser nsUser) {
        information.validateApply();
        amount.validatePrice(payment);
        Session paidSession = new PaidSession(this.id,
                                              this.information,
                                              this.image,
                                              totalAttendee.add(),
                                              this.amount);
        return new Attendee(nsUser.getId(), this.id);
    }
}
