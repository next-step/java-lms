package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CourseBuilder extends AuditInfo {

    private Long id = 1L;

    private String title = "";

    private Long creatorId = 1L;

    private Sessions sessions = new Sessions();

    public static CourseBuilder aCourse(){
        return new CourseBuilder();
    }

    private CourseBuilder(){
        super(LocalDateTime.now(), null);
    }

    private CourseBuilder(final CourseBuilder courseBuilder){
        super(LocalDateTime.now(), null);
        this.id = courseBuilder.id;
        this.creatorId = courseBuilder.creatorId;
        this.title = courseBuilder.title;
        this.sessions = courseBuilder.sessions;
    }

    public CourseBuilder withId(final Long id){
        this.id = id;
        return new CourseBuilder(this);
    }

    public CourseBuilder withTitle(final String title) {
        this.title = title;
        return new CourseBuilder(this);
    }

    public CourseBuilder withCreatorId(final Long creatorId) {
        this.creatorId = creatorId;
        return new CourseBuilder(this);
    }

    public CourseBuilder withSessions(Sessions sessions) {
        this.sessions = sessions;
        return new CourseBuilder(this);
    }

    public CourseBuilder withCreatedAt(LocalDateTime createAt) {
        this.createdAt = createAt;
        return new CourseBuilder(this);
    }

    public CourseBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return new CourseBuilder(this);
    }

    public Course build() {
        return new Course(id, title, creatorId, sessions, LocalDateTime.now(), LocalDateTime.now());
    }
}
