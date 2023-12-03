package nextstep.courses.domain.session;

public class Session {
    private final Long id;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;

    public Session(final Long id, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
    }
}
