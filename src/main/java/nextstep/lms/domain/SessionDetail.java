package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionProgressEnum;
import nextstep.lms.enums.SessionRecruitmentEnum;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionDetail {
    private final SessionEnrollmentManagement sessionEnrollmentManagement;
    private final SessionProgressEnum sessionProgressEnum;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(PricingTypeEnum pricingTypeEnum, Long tuitionFee, SessionProgressEnum sessionProgressEnum, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(new SessionEnrollmentManagement(pricingTypeEnum, tuitionFee, sessionRecruitmentEnum, capacity), sessionProgressEnum, new SessionPeriod(startDate, endDate));
    }

    public SessionDetail(SessionEnrollmentManagement sessionEnrollmentManagement, SessionProgressEnum sessionProgressEnum, SessionPeriod sessionPeriod) {
        this.sessionEnrollmentManagement = sessionEnrollmentManagement;
        this.sessionProgressEnum = sessionProgressEnum;
        this.sessionPeriod = sessionPeriod;
    }

    public void enrollableCheck(Students students, Payment payment) {
        sessionPeriod.canEnrollCheck(LocalDateTime.now());
        sessionEnrollmentManagement.enrollableCheck(students, payment);
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


