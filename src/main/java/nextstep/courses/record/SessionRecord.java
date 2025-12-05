package nextstep.courses.record;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.constant.SessionStatus;

import java.time.LocalDateTime;

public class SessionRecord {

    private Long id;
    private Long courseId;
    private Long coverImageId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int maxCapacity;
    private Long tuition;
    private String sessionType;
    private String sessionStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SessionRecord(Long id, Long courseId, Long coverImageId,
                         LocalDateTime startDate, LocalDateTime endDate,
                         int maxCapacity, Long tuition,
                         String sessionType, String sessionStatus,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.coverImageId = coverImageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxCapacity = maxCapacity;
        this.tuition = tuition;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session toSession(Course course, CoverImage coverImage, Enrollments enrollments) {
        return new Session(this.id, course, createdSessionRange(), createdSessionPolicy(),
                SessionStatus.from(this.sessionStatus), coverImage, enrollments);
    }

    private SessionRange createdSessionRange() {
        return new SessionRange(this.startDate, this.endDate);
    }

    private SessionPolicy createdSessionPolicy() {
        return new SessionPolicy(this.maxCapacity, this.tuition, this.sessionType);
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getCoverImageId() {
        return coverImageId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Long getTuition() {
        return tuition;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getSessionType() {
        return sessionType;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
