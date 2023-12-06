package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

import java.time.LocalDateTime;

public class SessionDetail {
    private final SessionEnrollmentManagement sessionEnrollmentManagement;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(String pricingType, int tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(new SessionEnrollmentManagement(pricingType, tuitionFee, sessionStatus, capacity), new SessionPeriod(startDate, endDate));
    }
    public SessionDetail(SessionEnrollmentManagement sessionEnrollmentManagement, SessionPeriod sessionPeriod) {
        this.sessionEnrollmentManagement = sessionEnrollmentManagement;
        this.sessionPeriod = sessionPeriod;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        if (sessionPeriod.canEnroll(LocalDateTime.now())) {
            sessionEnrollmentManagement.enroll(students, enrollApplicationDTO);
        }
    }

    public void sessionStatusCheck() {
        sessionEnrollmentManagement.sessionStatusCheck();
    }

    public String getPricingType() {
        return sessionEnrollmentManagement.getPricingType();
    }
    public int getTuitionFee() {
        return sessionEnrollmentManagement.getTuitionFee();
    }
    public String getSessionStatus() {
        return sessionEnrollmentManagement.getSessionStatus();
    }
    public int getCapacity() {
        return sessionEnrollmentManagement.getCapacity();
    }
    public LocalDateTime getStartDate() {
        return sessionPeriod.getStartDate();
    }
    public LocalDateTime getEndDate() {
        return sessionPeriod.getEndDate();
    }

    @Override
    public String toString() {
        return "SessionDetail{" +
                "sessionEnrollmentManagement=" + sessionEnrollmentManagement +
                ", sessionPeriod=" + sessionPeriod +
                '}';
    }
}


