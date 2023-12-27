package nextstep.courses.domain.course;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.Sessions;

import java.time.LocalDateTime;

public class Course extends BaseEntity {
    private final Long id;

    private final String title;

    private final int ordering;

    private final Sessions sessions;

    public Course(String title, int ordering, Long creatorId, LocalDateTime date) {
        this(0L, title, ordering, new Sessions(), creatorId, date, null);
    }

    public Course(Long id, String title, int ordering, Sessions sessions,
                  Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public int ordering() {
        return this.ordering;
    }

    public Sessions sessions() {
        return this.sessions;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ordering=" + ordering +
                ", sessions=" + sessions +
                '}';
    }
}
