package nextstep.courses.domain;

public enum SessionFixture {
    TDD_SESSION(SessionInformationFixture.TDD_SESSION_INFORMATION.sessionInformation(),
            SessionStatus.RECRUIT,
            SessionAttendeesFixture.TDD_SESSION_ATTENDEES.sessionAttendees());

    private final SessionInformation information;
    private final SessionStatus status;
    private final SessionAttendees attendees;

    SessionFixture(SessionInformation information, SessionStatus status, SessionAttendees attendees) {
        this.information = information;
        this.status = status;
        this.attendees = attendees;
    }

    public Session session() {
        return new Session(information, status, attendees);
    }
}
