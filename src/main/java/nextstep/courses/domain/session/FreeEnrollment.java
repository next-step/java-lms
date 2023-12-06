package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.Attendees;

public class FreeEnrollment implements Enrollment {

    private final Attendees attendees;

    public FreeEnrollment(Attendees attendees) {
        this.attendees = attendees;
    }

    @Override
    public Attendee enroll(Long amount, Long userId, Long sessionId) {
        Attendee attendee = new Attendee(userId, sessionId);
        attendees.checkAlreadyAttend(attendee);
        return attendee;
    }
}
