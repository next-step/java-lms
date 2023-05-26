package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class CourseBuilder {
    private Long id;
    private String title;
    private Long creatorId;
    private List<Session> sessions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CourseBuilder() {
    }

    public static CourseBuilder init() {
        return new CourseBuilder();
    }

    public CourseBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CourseBuilder title(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder creatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public CourseBuilder sessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public CourseBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CourseBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Course build() {
        return new Course(id, title, creatorId, sessions, createdAt, updatedAt);
    }

}
