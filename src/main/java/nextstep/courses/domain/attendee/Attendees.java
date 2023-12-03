package nextstep.courses.domain.attendee;

import nextstep.courses.exception.AlreadyTakingSessionException;

import java.util.ArrayList;
import java.util.List;

public class Attendees {

    private final List<Attendee> values;

    public Attendees() {
        this(new ArrayList<>());
    }

    public Attendees(List<Attendee> values) {
        this.values = values;
    }

    public void contains(Attendee attendee) {
        if (this.values.contains(attendee)) {
            throw new AlreadyTakingSessionException();
        }
    }
}
