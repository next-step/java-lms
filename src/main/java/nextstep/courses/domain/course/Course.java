package nextstep.courses.domain.course;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.Sessions;

import java.time.LocalDateTime;

public class Course extends BaseEntity {
    private Long id;

    private String title;

    private int ordering;

    private Sessions sessions;

    public Course(String title, int ordering, Long creatorId, LocalDateTime date) {
        this(0L, title, ordering, creatorId, date, null);
    }

    public Course(Long id, String title, int ordering, Long creatorId,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.sessions = new Sessions();
    }

    public int sessionSize() {
        return this.sessions.size();
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getOrdering() {
        return this.ordering;
    }

    public Long getCreatorId() {
        return super.getCreatorId();
    }
}
