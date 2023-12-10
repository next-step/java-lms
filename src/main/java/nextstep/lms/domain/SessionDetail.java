package nextstep.lms.domain;

import nextstep.lms.enums.SessionProgressEnum;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionDetail {
    private final SessionEnrollmentManagement sessionEnrollmentManagement;
    private final SessionProgressEnum sessionProgressEnum;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(String pricingType, Long tuitionFee, String sessionProgress, String sessionRecruitment, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(new SessionEnrollmentManagement(pricingType, tuitionFee, sessionRecruitment, capacity), SessionProgressEnum.valueOf(sessionProgress), new SessionPeriod(startDate, endDate));
    }

    public SessionDetail(SessionEnrollmentManagement sessionEnrollmentManagement, SessionProgressEnum sessionProgressEnum, SessionPeriod sessionPeriod) {
        this.sessionEnrollmentManagement = sessionEnrollmentManagement;
        this.sessionProgressEnum = sessionProgressEnum;
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

    public String getSessionRecruitment() {
        return sessionEnrollmentManagement.getSessionRecruitment();
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

    public String getSessionProgressEnum() {
        return sessionProgressEnum.name();
    }
}


