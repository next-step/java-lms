package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import java.util.Objects;

import nextstep.courses.domain.session.Session;

public class Course {

    private final Long id;
    private final String title;
    private final Long creatorId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private Sessions sessions;

    public Course(final String title, final Long creatorId) {
        this(null, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(
            final Long id,
            final String title,
            final Long creatorId,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions = new Sessions();
    }

    public Long id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public Long creatorId() {
        return this.creatorId;
    }

    public LocalDateTime createdAt() {
        return this.createdAt;
    }

    public LocalDateTime updatedAt() {
        return this.updatedAt;
    }

    public void assignSessions(final Sessions sessions) {
        this.sessions = sessions;
    }

    public void addNewSession(final Session session) {
        this.sessions.add(session);
        session.assignCourse(this);
    }

    public boolean containsSession(final Session session) {
        return this.sessions.contains(session);
    }

    @Override
    public boolean equals(final Object otherCourse) {
        if (this == otherCourse) {
            return true;
        }

        if (otherCourse == null || getClass() != otherCourse.getClass()) {
            return false;
        }

        final Course that = (Course)otherCourse;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.creatorId, that.creatorId) &&
                Objects.equals(this.createdAt, that.createdAt) &&
                Objects.equals(this.updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.creatorId, this.createdAt, this.updatedAt);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + this.id +
                ", title='" + this.title + '\'' +
                ", creatorId=" + this.creatorId +
                ", createdAt=" + this.createdAt +
                ", updatedAt=" + this.updatedAt +
                '}';
    }
}
