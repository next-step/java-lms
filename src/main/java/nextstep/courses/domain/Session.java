package nextstep.courses.domain;

import javax.swing.plaf.IconUIResource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String coverImage;
    private Type type;
    private Status status;
    private int numberOfRegisteredStudent = 0;
    private int maxNumberOfStudents;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDate startDate, LocalDate endDate, Long courseId) {
        this("test", startDate, endDate, "cover", Type.FREE, Status.READY, 30, courseId);
    }

    public Session(Status status) {
        this("test", LocalDate.now(), LocalDate.now().plusDays(30), "cover", Type.FREE, status, 30, 1L);
    }

    public Session(int maxNumberOfStudents) {
        this("test", LocalDate.now(), LocalDate.now().plusDays(30), "image", Type.FREE, Status.RECRUIT, maxNumberOfStudents, 1L);
    }

    public Session(String name, LocalDate startDate, LocalDate endDate, String coverImage, Type type, Status status, int maxNumberOfStudents, Long courseId) {
        this(name, startDate, endDate, coverImage, type, status, 0, maxNumberOfStudents, courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(String name, LocalDate startDate, LocalDate endDate, String coverImage, Type type, Status status, int numberOfRegisteredStudent, int maxNumberOfStudents, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateDate(startDate, endDate);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.type = type;
        this.status = status;
        this.numberOfRegisteredStudent = numberOfRegisteredStudent;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register() {
        validateCapacityOfStudent();
        validateStatusOfSession();
        numberOfRegisteredStudent++;
    }

    private void validateDate(LocalDate startedAt, LocalDate endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("강의 시작일은 강의 종료일 보다 빨라야 합니다.");
        }
    }

    private void validateStatusOfSession() {
        if (Status.isNotRecruit(status)) {
            throw new IllegalStateException("현재 강의 모집 중이 아닙니다.");
        }
    }

    private void validateCapacityOfStudent() {
        if (numberOfRegisteredStudent + 1 > maxNumberOfStudents) {
            throw new IllegalStateException("현재 최대 수강 인원이 초과 되었습니다.");
        }
    }

    public int getNumberOfRegisteredStudent() {
        return numberOfRegisteredStudent;
    }

    public String getName() {
        return name;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public Type getType() {
        return type;
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return type == session.type && maxNumberOfStudents == session.maxNumberOfStudents && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate) && Objects.equals(coverImage, session.coverImage) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, coverImage, type, status, maxNumberOfStudents);
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coverImage='" + coverImage + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", numberOfRegisteredStudent=" + numberOfRegisteredStudent +
                ", maxNumberOfStudents=" + maxNumberOfStudents +
                ", courseId=" + courseId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
