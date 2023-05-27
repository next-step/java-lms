package nextstep.courses.domain;

public enum SessionFixture {
    TDD_SESSION(1L,
            SessionInformationFixture.TDD_SESSION_INFORMATION.sessionInformation(),
            SessionStatus.RECRUIT,
            SessionAttendeesFixture.TDD_SESSION_ATTENDEES.sessionAttendees());

    private final Long id;
    private final SessionInformation information;
    private final SessionStatus status;
    private final SessionAttendees attendees;

    SessionFixture(Long id, SessionInformation information, SessionStatus status, SessionAttendees attendees) {
        this.id = id;
        this.information = information;
        this.status = status;
        this.attendees = attendees;
    }

    public Session session() {
        return new Session(id, information, status, attendees);
    }
}
