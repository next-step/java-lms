package nextstep.lms.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionInfo {
    private final List<CoverImage> coverImages;
    private final SessionDetail sessionDetail;

    public SessionInfo(List<CoverImage> coverImages, String pricingType, Long tuitionFee, String sessionProgress, String sessionRecruitment, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(coverImages, new SessionDetail(pricingType, tuitionFee, sessionProgress, sessionRecruitment, capacity, startDate, endDate));
    }

    public SessionInfo(List<CoverImage> coverImages, SessionDetail sessionDetail) {
        this.coverImages = new ArrayList<>(coverImages) ;
        this.sessionDetail = sessionDetail;
    }

    public Long enroll(Students students, Payment payment) {
        return sessionDetail.enroll(students, payment);
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
