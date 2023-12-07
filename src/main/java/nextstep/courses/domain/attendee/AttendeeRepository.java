package nextstep.courses.domain.attendee;

import java.util.Optional;

public interface AttendeeRepository {

    Optional<Attendee> findById(Long attendeeId);

    void save(Attendee attendee);
}
