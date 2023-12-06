package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;

public interface Enrollment {

    Attendee enroll(Long amount, Long userId, Long sessionId);
}
