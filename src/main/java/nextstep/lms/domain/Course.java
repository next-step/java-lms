package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Course {
    private Long id;

    private String title;

    private LmsUser creator;

    private List<Session> sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Course() {
    }

    public Course(Long id, String title, LmsUser creator, List<Session> sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private static void valiateCreatorAuthorization(LmsUser creator) {
        if (creator.isNotAdmin()) {
            throw new UnAuthorizedException("과정 생성 권한이 없는 유저입니다.");
        }
    }

    public static Course of(String title, LmsUser creator) {
        Utils.validateTile(title);
        valiateCreatorAuthorization(creator);
        return new Course(null, title, creator, new ArrayList<>(), LocalDateTime.now(), null);
    }

    public boolean isSameCreator(LmsUser sessionCreator) {
        return creator.equals(sessionCreator);
    }

    public void addSession(Session newSession) {
        sessions.add(newSession);
    }

    public boolean isSameTitle(String title) {
        return this.title.equals(title);
    }

    public boolean hasSession(Session session) {
        return this.sessions.contains(session);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creator.getId();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creator=" + creator +
                ", sessions=" + sessions +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(creator, course.creator) && Objects.equals(sessions, course.sessions) && Objects.equals(createdAt, course.createdAt) && Objects.equals(updatedAt, course.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creator, sessions, createdAt, updatedAt);
    }
}
