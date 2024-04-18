package nextstep.courses.domain;

import java.util.Objects;

public class SessionInfo {
    private final String title;

    private final Long sessionId;

    public SessionInfo(String title, Long sessionId) {
        this.title = title;
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, sessionId);
    }
}
