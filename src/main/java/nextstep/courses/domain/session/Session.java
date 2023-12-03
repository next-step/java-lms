package nextstep.courses.domain.session;

public class Session {
    private final Long id;
    private final SessionPeriod sessionPeriod;

    public Session(final Long id, final SessionPeriod sessionPeriod) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
    }
}
