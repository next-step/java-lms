package nextstep.courses.domain;

import nextstep.courses.domain.course.Sessions;
import nextstep.courses.tobe.domain.TobePaidSession;
import nextstep.courses.tobe.domain.TobeSession;
import nextstep.courses.tobe.domain.course.TobeSessions;

import java.time.LocalDateTime;
import java.util.Objects;

public class Course {
    private Long id;

    private String title;

    private Sessions sessions = new Sessions();

    private TobeSessions tobeSessions = new TobeSessions();

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void addTobeSession(TobeSession session) {
        this.tobeSessions.add(session);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(creatorId, course.creatorId) && Objects.equals(sessions, course.sessions) && Objects.equals(tobeSessions, course.tobeSessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creatorId, sessions, tobeSessions);
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
