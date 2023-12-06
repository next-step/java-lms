package nextstep.courses.domain.attendee;

public interface AttendeeRepository {

    Attendees findAllBySessionId(Long sessionId);

    void save(Attendee attendee);
}
