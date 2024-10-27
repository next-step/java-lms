package nextstep.courses.domain;

import nextstep.session.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long term;

    private List<Session> sessions = new ArrayList<>();

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course(Long id, String title, Long term, Long creatorId) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();;
    }

    public String getTitle() {
        return title;
    }

    public List<Session> getLectures() {
        return sessions;
    }

    public void addSession(Session lecture) {
        this.sessions.add(lecture);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
