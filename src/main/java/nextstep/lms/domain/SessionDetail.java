package nextstep.lms.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionDetail {
    private final SessionEnrollmentManagement sessionEnrollmentManagement;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(String pricingType, Long tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(new SessionEnrollmentManagement(pricingType, tuitionFee, sessionStatus, capacity), new SessionPeriod(startDate, endDate));
    }

    public SessionDetail(SessionEnrollmentManagement sessionEnrollmentManagement, SessionPeriod sessionPeriod) {
        this.sessionEnrollmentManagement = sessionEnrollmentManagement;
        this.sessionPeriod = sessionPeriod;
    }

    public Long enroll(Students students, Payment payment) {
        sessionPeriod.canEnrollCheck(LocalDateTime.now());
        return sessionEnrollmentManagement.enroll(students, payment);
    }

    public String getPricingType() {
        return sessionEnrollmentManagement.getPricingType();
    }

    public Long getTuitionFee() {
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

}


