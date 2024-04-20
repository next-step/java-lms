package nextstep.sessions.domain.builder;

import nextstep.sessions.domain.Session;

import java.time.LocalDateTime;

public class SessionBuilder {

    private final long id = 1L;

    private final LocalDateTime startedAt = LocalDateTime.of(2024, 1, 1, 0, 0);

    private final LocalDateTime endedAt = LocalDateTime.of(2024, 12, 31, 23, 59);

    private String sessionName;

    public SessionBuilder withSessionName(String sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public Session build() {
        return new Session(id, startedAt, endedAt, sessionName);
    }

}
