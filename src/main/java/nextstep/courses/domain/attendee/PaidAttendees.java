package nextstep.courses.domain.attendee;

import nextstep.courses.exception.AlreadyTakingSessionException;
import nextstep.courses.exception.ExceedAttendeesException;
import nextstep.courses.exception.NegativeOrZeroNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaidAttendees {

    private final List<Attendee> values;

    private final int maxCapacity;

    public PaidAttendees(int maxCapacity) {
        this(new ArrayList<>(), maxCapacity);
    }

    public PaidAttendees(List<Attendee> values, int maxCapacity) {
        validateCapacity(maxCapacity);
        this.values = values;
        this.maxCapacity = maxCapacity;
    }

    private void validateCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new NegativeOrZeroNumberException();
        }
    }

    public void checkParticipateIn(Attendee attendee) {
        checkAlreadyAttend(attendee);
        validateMaxCapacity();
    }

    private void checkAlreadyAttend(Attendee attendee) {
        if (this.values.contains(attendee)) {
            throw new AlreadyTakingSessionException();
        }
    }

    private void validateMaxCapacity() {
        if (this.values.size() + 1 > maxCapacity) {
            throw new ExceedAttendeesException(this.values.size());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidAttendees attendees = (PaidAttendees) o;
        return maxCapacity == attendees.maxCapacity && Objects.equals(values, attendees.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, maxCapacity);
    }
}
