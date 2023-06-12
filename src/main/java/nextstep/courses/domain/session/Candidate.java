package nextstep.courses.domain.session;

import java.util.Objects;

public class Candidate {
    private final Long sessionId;

    private final Long userId;

    public Candidate(Long sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(sessionId, candidate.sessionId) && Objects.equals(userId, candidate.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId);
    }
}
