package nextstep.courses.domain;

import nextstep.common.domain.BaseControlField;

import java.time.LocalDateTime;

public class Course extends BaseControlField {
    private Long id;

    private String title;

    private Sessions sessions = new Sessions();

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean registerSession(Session session) {
        return sessions.addSession(session);
    }

    @Override
    public String toString() {
        String controlField  = super.toString();
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}' + controlField;
    }
}
