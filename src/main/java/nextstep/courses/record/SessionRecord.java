package nextstep.courses.record;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.constant.SessionRecruitmentStatus;
import nextstep.courses.domain.session.constant.SessionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class SessionRecord {

    private Long id;
    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int maxCapacity;
    private Long tuition;
    private String sessionType;
    private String sessionStatus;
    private String recruitmentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SessionRecord(Long id, Long courseId,
                         LocalDateTime startDate, LocalDateTime endDate,
                         int maxCapacity, Long tuition,
                         String sessionType, String sessionStatus, String recruitmentStatus,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxCapacity = maxCapacity;
        this.tuition = tuition;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SessionRange createdSessionRange() {
        return new SessionRange(this.startDate, this.endDate);
    }

    public SessionPolicy createdSessionPolicy() {
        return new SessionPolicy(this.maxCapacity, this.tuition, this.sessionType);
    }

    public SessionCore createdSessionCore() {
        return new SessionCore(
                createdSessionRange(),
                createdSessionPolicy(),
                SessionStatus.from(this.sessionStatus),
                SessionRecruitmentStatus.from(this.recruitmentStatus)
        );
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
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

    public String getRecruitmentStatus() {
        return recruitmentStatus;
    }

}
