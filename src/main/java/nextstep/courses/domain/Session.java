package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String coverImage;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private int numberOfRegisteredStudent = 0;
    private int maxNumberOfStudents;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDate startDate, LocalDate endDate, Long courseId) {
        this("test", startDate, endDate, "cover", SessionType.FREE, SessionStatus.READY, 30, courseId);
    }

    public Session(SessionStatus sessionStatus) {
        this("test", LocalDate.now(), LocalDate.now().plusDays(30), "cover", SessionType.FREE, sessionStatus, 30, 1L);
    }

    public Session(int maxNumberOfStudents) {
        this("test", LocalDate.now(), LocalDate.now().plusDays(30), "image", SessionType.FREE, SessionStatus.RECRUIT, maxNumberOfStudents, 1L);
    }

    public Session(String name, LocalDate startDate, LocalDate endDate, String coverImage, SessionType sessionType, SessionStatus sessionStatus, int maxNumberOfStudents, Long courseId) {
        this(name, startDate, endDate, coverImage, sessionType, sessionStatus, 0, maxNumberOfStudents, courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(String name, LocalDate startDate, LocalDate endDate, String coverImage, SessionType sessionType, SessionStatus sessionStatus, int numberOfRegisteredStudent, int maxNumberOfStudents, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateDate(startDate, endDate);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
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
        if (SessionStatus.isNotRecruit(sessionStatus)) {
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

    public SessionStatus getStatus() {
        return sessionStatus;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public SessionType getType() {
        return sessionType;
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return sessionType == session.sessionType && maxNumberOfStudents == session.maxNumberOfStudents && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate) && Objects.equals(coverImage, session.coverImage) && sessionStatus == session.sessionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, coverImage, sessionType, sessionStatus, maxNumberOfStudents);
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coverImage='" + coverImage + '\'' +
                ", type=" + sessionType +
                ", status=" + sessionStatus +
                ", numberOfRegisteredStudent=" + numberOfRegisteredStudent +
                ", maxNumberOfStudents=" + maxNumberOfStudents +
                ", courseId=" + courseId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
