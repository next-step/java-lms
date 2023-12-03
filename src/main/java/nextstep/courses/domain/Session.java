package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.PaidType.FREE;
import static nextstep.courses.domain.PaidType.PAID;
import static nextstep.courses.domain.SessionStatus.APPLYING;


public class Session {
    private Long id;
    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ImageInfo imageInfo;
    private PaidType paidType;
    private Integer targetNumber;
    private Integer appliedNumber;
    private Long sessionFee;
    private SessionStatus sessionStatus;


    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, ImageInfo imageInfo, PaidType paidType, Integer targetNumber, Integer appliedNumber, Long sessionFee, SessionStatus sessionStatus) {
        if (paidType.equals(FREE) && targetNumber != null) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원 제한이 없습니다.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("강의 시작일은 종료일 이후일 수 없습니다.");
        }
        this.id = id;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageInfo = imageInfo;
        this.paidType = paidType;
        this.targetNumber = targetNumber;
        this.appliedNumber = appliedNumber;
        this.sessionFee = sessionFee;
        this.sessionStatus = sessionStatus;
    }

    public String period() {
        String startDate = String.valueOf(this.startDate);
        String endDate = String.valueOf(this.endDate);
        return startDate + "~" + endDate;
    }

    public boolean isValidNumberOfStudents() {
        return paidType.equals(PAID) && Objects.equals(targetNumber, appliedNumber);
    }

    public boolean isValidPayAmount(Payment payment) {
        return paidType.equals(PAID) && !Objects.equals(payment.getAmount(), sessionFee);
    }

    public boolean isValidStatus() {
        return sessionStatus != APPLYING;
    }

    public void increaseAppNumber() {
        this.appliedNumber = appliedNumber + 1;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
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
}
