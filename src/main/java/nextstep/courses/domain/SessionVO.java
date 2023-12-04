package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionVO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ImageInfo imageInfo;
    private PaidType paidType;
    private Integer targetNumber;
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

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public PaidType getPaidType() {
        return paidType;
    }

    public Integer getTargetNumber() {
        return targetNumber;
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
