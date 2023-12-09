package nextstep.lms.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionInfo {
    private final CoverImage coverImage;
    private final SessionDetail sessionDetail;

    public SessionInfo(CoverImage coverImage, String pricingType, Long tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(coverImage, new SessionDetail(pricingType, tuitionFee, sessionStatus, capacity, startDate, endDate));
    }

    public SessionInfo(CoverImage coverImage, SessionDetail sessionDetail) {
        this.coverImage = coverImage;
        this.sessionDetail = sessionDetail;
    }

    public Long enroll(Students students, Payment payment) {
        return sessionDetail.enroll(students, payment);
    }

    public Long getImageId() {
        return coverImage.getId();
    }

    public String getPricingType() {
        return sessionDetail.getPricingType();
    }

    public Long getTuitionFee() {
        return sessionDetail.getTuitionFee();
    }

    public String getSessionStatus() {
        return sessionDetail.getSessionStatus();
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
