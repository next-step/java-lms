package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public final class CourseBuilder {
    private Long id;
    private String title;
    private Long creatorId;
    private List<Session> sessions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CourseBuilder() {
    }

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public CourseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CourseBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder withCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public CourseBuilder withSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public CourseBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CourseBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Course build() {
        return new Course(id, title, creatorId, sessions, createdAt, updatedAt);
    }
}
