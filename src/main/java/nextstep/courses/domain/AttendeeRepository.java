package nextstep.courses.domain;

public interface AttendeeRepository {

    Attendees findAllBySessionId(Long sessionId);

    void save(Attendee attendee);
}
