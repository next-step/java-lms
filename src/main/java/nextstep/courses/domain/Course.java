package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.dto.CourseDTO;
import nextstep.users.domain.NsUser;

public class Course extends AuditInfo{
    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    public Course(Long id, String title, Long creatorId, Sessions sessions, LocalDateTime createAt, LocalDateTime updateAt) {
        super(createAt, updateAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }

    public Course() {
        super(LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id){
        this(id, "", -1L, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Long creatorId, Sessions sessions) {
        this(id, title, creatorId, sessions, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createAt, LocalDateTime updateAt) {
        this(id, title, creatorId, new Sessions(), createAt, updateAt);
    }

    public void addSessions(Sessions sessions){
        this.sessions.addAll(sessions);
    }

    public Session enroll(NsUser user, Long sessionId) {
        return sessions.enroll(user, sessionId);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public CourseDTO toDto(){
        return new CourseDTO(id, title, creatorId, sessions);
    }
}
