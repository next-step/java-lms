package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.FreeAttendees;

public class FreeEnrollment implements Enrollment {

    private final FreeAttendees freeAttendees;

    public FreeEnrollment(FreeAttendees freeAttendees) {
        this.freeAttendees = freeAttendees;
    }

    @Override
    public Attendee enroll(Long amount, Long userId, Long sessionId) {
        Attendee attendee = new Attendee(userId, sessionId);
        freeAttendees.checkAlreadyAttend(attendee);
        return attendee;
    }
}
