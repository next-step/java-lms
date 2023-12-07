package nextstep.courses.domain.attendee;

import nextstep.courses.exception.AlreadyTakingSessionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreeAttendees {

    private final List<Attendee> values;

    public FreeAttendees() {
        this(new ArrayList<>());
    }

    public FreeAttendees(List<Attendee> values) {
        this.values = values;
    }

    public void checkAlreadyAttend(Attendee attendee) {
        if (this.values.contains(attendee)) {
            throw new AlreadyTakingSessionException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeAttendees that = (FreeAttendees) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
