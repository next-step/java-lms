package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.Attendees;
import nextstep.courses.exception.ExceedAttendeesException;
import nextstep.courses.exception.NegativeOrZeroNumberException;

public class PaidEnrollment implements Enrollment {

    private final Attendees attendees;

    private final Price price;

    private final int maxCapacity;

    public PaidEnrollment(Attendees attendees,
                          Price price,
                          int maxCapacity) {
        validateCapacity(maxCapacity);
        this.attendees = attendees;
        this.price = price;
        this.maxCapacity = maxCapacity;
    }

    private void validateCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new NegativeOrZeroNumberException();
        }
    }

    public Attendee enroll(Long amount, Long userId, Long sessionId) {
        Attendee attendee = new Attendee(userId, sessionId);
        attendees.checkAlreadyAttend(attendee);
        validateMaxCapacity();
        price.validatePrice(amount);
        return attendee;
    }

    private void validateMaxCapacity() {
        if (attendees.size() + 1 > maxCapacity) {
            throw new ExceedAttendeesException(attendees.size());
        }
    }
}
