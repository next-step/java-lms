package nextstep.courses.domain;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseBuilder {

    private Long id;
    private String title;
    private List<Session> sessions;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CourseBuilder() {
    }

    private CourseBuilder(CourseBuilder copy) {
        this.id = copy.id;
        this.title = copy.title;
        this.sessions = copy.sessions;
        this.creatorId = copy.creatorId;
        this.createdAt = copy.createdAt;
        this.updatedAt = copy.updatedAt;
    }

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public CourseBuilder but() {
        return new CourseBuilder(this);
    }

    public CourseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CourseBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder withSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public CourseBuilder withSession(Session session) {
        addSession(session);
        return this;
    }

    public CourseBuilder with(SessionBuilder sessionBuilder) {
        addSession(sessionBuilder.build());
        return this;
    }

    private void addSession(Session session) {
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        sessions.add(session);
    }

    public CourseBuilder withCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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
        return new Course(id, title, sessions, creatorId, createdAt, updatedAt);
    }

}
