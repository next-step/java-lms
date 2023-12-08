package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionVO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Image image;
    private PaidType paidType;
    private Integer maxStudentNumber;
    private Integer appliedNumber;
    private Long sessionFee;
    private SessionStatus sessionStatus;

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Image getImage() {
        return image;
    }

    public PaidType getPaidType() {
        return paidType;
    }

    public Integer getMaxStudentNumber() {
        return maxStudentNumber;
    }

    public Integer getAppliedNumber() {
        return appliedNumber;
    }

    public Long getSessionFee() {
        return sessionFee;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
}
