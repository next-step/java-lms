package nextstep.courses.domain.attendee;

import nextstep.courses.exception.AlreadyTakingSessionException;

import java.util.ArrayList;
import java.util.List;

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
}
