package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final Students students;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, CoverImage coverImage, String pricingType, int tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, new SessionInfo(coverImage, pricingType, tuitionFee, sessionStatus, capacity, startDate, endDate), new Students(new ArrayList<>()), createdAt, updatedAt);
    }

    public Session(CoverImage coverImage, String pricingType, int tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(0l, new SessionInfo(coverImage, pricingType, tuitionFee, sessionStatus, capacity, startDate, endDate), new Students(new ArrayList<>()), LocalDateTime.now(), null);
    }

    public Session(Long id, SessionInfo sessionInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, sessionInfo, new Students(new ArrayList<>()), createdAt, updatedAt);
    }

    public Session(Long id, SessionInfo sessionInfo, Students students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.students = students;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void enroll(EnrollApplicationDTO enrollApplicationDTO) {
        sessionInfo.enroll(this.students, enrollApplicationDTO);
    }

    public Long getImageId() {
        return sessionInfo.getImageId();
    }

    public String getPricingType() {
        return sessionInfo.getPricingType();
    }

    public int getTuitionFee() {
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
