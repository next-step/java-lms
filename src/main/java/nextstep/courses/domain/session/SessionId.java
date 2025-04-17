package nextstep.courses.domain.session;

import lombok.Getter;

@Getter
public class SessionId {
    private final Long id;
    private final Long courseId;

    public SessionId(Long id, Long courseId) {
        this.id = id;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionId sessionId = (SessionId) o;
        return id.equals(sessionId.id) && courseId.equals(sessionId.courseId);
    }

    @Override
    public int hashCode() {
        return id.hashCode() * 31 + courseId.hashCode();
    }
}