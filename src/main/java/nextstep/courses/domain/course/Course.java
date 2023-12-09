package nextstep.courses.domain.course;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.Sessions;

import java.time.LocalDateTime;

public class Course extends BaseEntity {
    private Long id;

    private String title;

    private int sequence;

    private Sessions sessions;

    public Course(String title, int sequence, Long creatorId) {
        this(0L, title, sequence, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, int sequence, Long creatorId,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.sequence = sequence;
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

    public int getSequence() {
        return this.sequence;
    }

    public Long getCreatorId() {
        return this.getCreatorId();
    }
}
