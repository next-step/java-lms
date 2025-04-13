package nextstep.courses.domain.session;

public class SessionId {
    private final Long value;

    public SessionId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionId sessionId = (SessionId) o;
        return value.equals(sessionId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
} 