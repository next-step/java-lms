package nextstep.courses.domain;

import nextstep.utils.CommunicationTerm;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CommunicationTerm("과정")
public class Course {

    private CourseId courseId;
    private String title;
    private Long creatorId;
    @NotNull
    private List<Session> sessions = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//

    public Course(CourseId courseId, String title, Long creatorId, List<Session> sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.courseId = courseId;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Course of(Long courseId, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Course(
                new CourseId(courseId),
                title,
                creatorId,
                new ArrayList<>(),
                createdAt,
                updatedAt
        );
    }
    public static Course of(Long courseId, String title, Long creatorId) {
        return Course.of(courseId, title, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean isIncludeSession(Session session) {
        return this.sessions
                .stream()
                .anyMatch(target -> target.equals(session));
    }

    public void addSessions(Session... sessions) {
        this.sessions.addAll(Arrays.asList(sessions));
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public CourseId getCourseId() {
        return this.courseId;
    }
}
