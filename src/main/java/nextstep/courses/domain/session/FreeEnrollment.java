package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.FreeAttendees;

import java.util.Objects;

import static nextstep.courses.domain.attendee.Approval.*;

public class FreeEnrollment implements Enrollment {

    private final FreeAttendees freeAttendees;

    public FreeEnrollment(FreeAttendees freeAttendees) {
        this.freeAttendees = freeAttendees;
    }

    @Override
    public Attendee enroll(Long amount, Long userId, Long sessionId) {
        Attendee attendee = new Attendee(userId, sessionId, NOT_APPROVED);
        freeAttendees.add(attendee);
        return attendee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeEnrollment that = (FreeEnrollment) o;
        return Objects.equals(freeAttendees, that.freeAttendees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freeAttendees);
    }
}
