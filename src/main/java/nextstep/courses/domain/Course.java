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

    public Course(String title, Long term, Long creatorId) {
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();;
    }

    public Course(Long id, String title, Long term, Long creatorId) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();;
    }

    public Course(Long id, String title, Long term, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.sessions = sessions;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void addSession(Session lecture) {
        this.sessions.add(lecture);
    }

    public List<Session> getLectures() {
        return sessions;
    }

    public Long getId() {
        return id;
    }

    public Long getTerm() {
        return term;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
