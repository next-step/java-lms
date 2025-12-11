package nextstep.courses.infrastructure.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionEntity {
    private final Long id;
    private final Long courseId;
    private final int term;
    private final LocalDate startDay;
    private final LocalDate endDay;
    private final String state;
    private final String recruitmentStatus;
    private final String type;
    private final String approvalPolicy;
    private final Integer maxCapacity;
    private final Long tuitionFee;
    private final LocalDateTime createdAt;

    public SessionEntity(Long id, Long courseId, int term, LocalDate startDay, LocalDate endDay,
                         String state, String recruitmentStatus, String type, String approvalPolicy,
                         Integer maxCapacity, Long tuitionFee, LocalDateTime createdAt) {
        this.id = id;
        this.courseId = courseId;
        this.term = term;
        this.startDay = startDay;
        this.endDay = endDay;
        this.state = state;
        this.recruitmentStatus = recruitmentStatus;
        this.type = type;
        this.approvalPolicy = approvalPolicy;
        this.maxCapacity = maxCapacity;
        this.tuitionFee = tuitionFee;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public int getTerm() {
        return term;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public String getState() {
        return state;
    }

    public String getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public String getType() {
        return type;
    }

    public String getApprovalPolicy() {
        return approvalPolicy;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Long getTuitionFee() {
        return tuitionFee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}