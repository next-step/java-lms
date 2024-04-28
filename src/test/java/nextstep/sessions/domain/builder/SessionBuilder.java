package nextstep.sessions.domain.builder;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;

import java.time.LocalDateTime;

public class SessionBuilder {

    private final long id = 1L;

    private final LocalDateTime startedAt = LocalDateTime.of(2024, 1, 1, 0, 0);

    private final LocalDateTime endedAt = LocalDateTime.of(2024, 12, 31, 23, 59);

    private String sessionName;

    private SessionRegisterDetails sessionRegisterDetails;

    public SessionBuilder withSessionName(String sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public SessionBuilder withSessionRegisterDetails(SessionRegisterDetails sessionRegisterDetails) {
        this.sessionRegisterDetails = sessionRegisterDetails;
        return this;
    }

    public Session build() {
        return new Session(id, startedAt, endedAt, sessionName, sessionRegisterDetails);
    }

}
