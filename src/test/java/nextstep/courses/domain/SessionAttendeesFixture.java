package nextstep.courses.domain;

public enum SessionAttendeesFixture {
    TDD_SESSION_ATTENDEES(10);

    private final int maxNumberOfAttendees;

    SessionAttendeesFixture(int maxNumberOfAttendees) {
        this.maxNumberOfAttendees = maxNumberOfAttendees;
    }

    public SessionAttendees sessionAttendees() {
        return new SessionAttendees(maxNumberOfAttendees);
    }
}
