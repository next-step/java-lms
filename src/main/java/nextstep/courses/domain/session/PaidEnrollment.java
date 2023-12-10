package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.PaidAttendees;

import java.util.Objects;

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
        price.validatePrice(amount);
        attendees.add(attendee);
        return attendee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidEnrollment that = (PaidEnrollment) o;
        return Objects.equals(attendees, that.attendees) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendees, price);
    }
}
