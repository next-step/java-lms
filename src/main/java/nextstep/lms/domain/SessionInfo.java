package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionProgressEnum;
import nextstep.lms.enums.SessionRecruitmentEnum;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionInfo {
    private final List<CoverImage> coverImages;
    private final SessionDetail sessionDetail;

    public SessionInfo(List<CoverImage> coverImages, PricingTypeEnum pricingTypeEnum, Long tuitionFee, SessionProgressEnum sessionProgressEnum, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(coverImages, new SessionDetail(pricingTypeEnum, tuitionFee, sessionProgressEnum, sessionRecruitmentEnum, capacity, startDate, endDate));
    }

    public SessionInfo(List<CoverImage> coverImages, SessionDetail sessionDetail) {
        this.coverImages = new ArrayList<>(coverImages);
        this.sessionDetail = sessionDetail;
    }

    public void enrollableCheck(Students students, Payment payment) {
        sessionDetail.enrollableCheck(students, payment);
    }

    public List<CoverImage> getCoverImages() {
        return Collections.unmodifiableList(coverImages);
    }

    public String getPricingType() {
        return sessionDetail.getPricingType();
    }

    public Long getTuitionFee() {
        return sessionDetail.getTuitionFee();
    }

    public String getSessionRecruitment() {
        return sessionDetail.getSessionRecruitment();
    }

    public String getSessionProgressEnum() {
        return sessionDetail.getSessionProgressEnum();
    }

    public int getCapacity() {
        return sessionDetail.getCapacity();
    }

    public LocalDateTime getStartDate() {
        return sessionDetail.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionDetail.getEndDate();
    }

}
