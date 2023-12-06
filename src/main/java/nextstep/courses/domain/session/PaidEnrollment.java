package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.PaidAttendees;

public class PaidEnrollment implements Enrollment {

    private final PaidAttendees attendees;

    private final Price price;

    public PaidEnrollment(PaidAttendees attendees,
                          Price price) {
        this.attendees = attendees;
        this.price = price;
    }

    public Attendee enroll(Long amount, Long userId, Long sessionId) {
        Attendee attendee = new Attendee(userId, sessionId);
        attendees.checkParticipateIn(attendee);
        price.validatePrice(amount);
        return attendee;
    }
}
