package nextstep.lms.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final Students students;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, CoverImage coverImage, String pricingType, Long tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate, Students students,LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, new SessionInfo(coverImage, pricingType, tuitionFee, sessionStatus, capacity, startDate, endDate), students, createdAt, updatedAt);
    }

    public Session(CoverImage coverImage, String pricingType, Long tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(0L, new SessionInfo(coverImage, pricingType, tuitionFee, sessionStatus, capacity, startDate, endDate), new Students(new ArrayList<>()), LocalDateTime.now(), null);
    }

    public Session(Long id, SessionInfo sessionInfo, Students students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.students = students;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student enroll(Payment payment) {
        return new Student(sessionInfo.enroll(this.students, payment), id);
    }

    public Long getImageId() {
        return sessionInfo.getImageId();
    }

    public String getPricingType() {
        return sessionInfo.getPricingType();
    }

    public Long getTuitionFee() {
        return sessionInfo.getTuitionFee();
    }

    public String getSessionStatus() {
        return sessionInfo.getSessionStatus();
    }

    public int getCapacity() {
        return sessionInfo.getCapacity();
    }

    public LocalDateTime getStartDate() {
        return sessionInfo.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionInfo.getEndDate();
    }

}
