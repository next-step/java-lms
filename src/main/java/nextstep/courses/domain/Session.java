package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private String name;
    private SessionPeriod sessionPeriod;
    private String coverImage;
    private SessionOption sessionOption;
    private SessionStudent sessionStudent;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDate startDate, LocalDate endDate, Long courseId) {
        this("test", new SessionPeriod(startDate, endDate), "cover", new SessionOption(), new SessionStudent(), courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(SessionEnrollment sessionEnrollment) {
        this("test", new SessionPeriod(LocalDate.now()), "cover", new SessionOption(sessionEnrollment), new SessionStudent(), 1L);
    }

    public Session(int maxNumberOfStudents) {
        this("test", new SessionPeriod(LocalDate.now()), "image", new SessionOption(), new SessionStudent(maxNumberOfStudents), 1L);
    }

    public Session(String name, SessionPeriod sessionPeriod, String coverImage, SessionOption sessionOption, SessionStudent sessionStudent, Long courseId) {
        this(name, sessionPeriod, coverImage, sessionOption, sessionStudent, courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(String name, SessionPeriod sessionPeriod, String coverImage, SessionOption sessionOption, SessionStudent sessionStudent, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionOption = sessionOption;
        this.sessionStudent = sessionStudent;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register() {
        sessionOption.validateStatusOfSession();
        sessionStudent.register();
    }

    public int getNumberOfRegisteredStudent() {
        return sessionStudent.getNumberOfRegisteredStudent();
    }

    public String getName() {
        return name;
    }

    public int getMaxNumberOfStudents() {
        return sessionStudent.getMaxNumberOfStudents();
    }

    public LocalDate getEndDate() {
        return sessionPeriod.getEndDate();
    }

    public LocalDate getStartDate() {
        return sessionPeriod.getStartDate();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(name, session.name) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(coverImage, session.coverImage) && Objects.equals(sessionOption, session.sessionOption) && Objects.equals(sessionStudent, session.sessionStudent) && Objects.equals(courseId, session.courseId) && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sessionPeriod, coverImage, sessionOption, sessionStudent, courseId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                ", sessionPeriod=" + sessionPeriod +
                ", coverImage='" + coverImage + '\'' +
                ", sessionOption=" + sessionOption +
                ", sessionStudent=" + sessionStudent +
                ", courseId=" + courseId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
