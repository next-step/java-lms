package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionProgressEnum;
import nextstep.lms.enums.SessionRecruitmentEnum;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final Students students;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, List<CoverImage> coverImages, PricingTypeEnum pricingTypeEnum, Long tuitionFee, SessionProgressEnum sessionProgressEnum, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity, LocalDateTime startDate, LocalDateTime endDate, Students students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, new SessionInfo(coverImages, pricingTypeEnum, tuitionFee, sessionProgressEnum, sessionRecruitmentEnum, capacity, startDate, endDate), students, createdAt, updatedAt);
    }

    public Session(List<CoverImage> coverImages, PricingTypeEnum pricingTypeEnum, Long tuitionFee, SessionProgressEnum sessionProgressEnum, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(0L, new SessionInfo(coverImages, pricingTypeEnum, tuitionFee, sessionProgressEnum, sessionRecruitmentEnum, capacity, startDate, endDate), new Students(new ArrayList<>()), LocalDateTime.now(), null);
    }

    public Session(Long id, SessionInfo sessionInfo, Students students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.students = students;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student enroll(Payment payment) {
        sessionInfo.enrollableCheck(this.students, payment);
        Student enrolledStudent = new Student(payment.getNsUserId(), id);
        return enrolledStudent;
    }

    public List<CoverImage> getCoverImages() {
        return sessionInfo.getCoverImages();
    }

    public String getPricingType() {
        return sessionInfo.getPricingType();
    }

    public Long getTuitionFee() {
        return sessionInfo.getTuitionFee();
    }

    public String getSessionRecruitment() {
        return sessionInfo.getSessionRecruitment();
    }

    public String getSessionProgressEnum() {
        return sessionInfo.getSessionProgressEnum();
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
