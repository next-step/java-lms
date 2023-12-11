package nextstep.courses.domain.attendee;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository {

    Optional<Attendee> findById(Long attendeeId);

    void save(Attendee attendee);

    List<Attendee> findAllBySessionId(Long sessionId);

    void update(Attendee approvedAttendee);
}
