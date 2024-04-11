package nextstep.session.dto;

import java.time.LocalDateTime;

public class SessionVO {

    private final long id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String sessionStatus;
    private final long courseId;
    private final int maxCapacity;
    private final int enrolled;
    private final long price;
    private final String tutorId;
    private final long coverId;
    private final String sessionName;
    private final boolean deleted;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public SessionVO(
            long id, LocalDateTime startDate, LocalDateTime endDate, String sessionStatus, long courseId,
            int maxCapacity, int enrolled, long price, String tutorId, long coverId, String sessionName,
            boolean deleted, LocalDateTime createdAt, LocalDateTime lastModifiedAt
    ) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionStatus = sessionStatus;
        this.courseId = courseId;
        this.maxCapacity = maxCapacity;
        this.enrolled = enrolled;
        this.price = price;
        this.tutorId = tutorId;
        this.coverId = coverId;
        this.sessionName = sessionName;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public long getCourseId() {
        return courseId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public long getPrice() {
        return price;
    }

    public String getTutorId() {
        return tutorId;
    }

    public long getCoverId() {
        return coverId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }
}
