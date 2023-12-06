package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

import java.time.LocalDateTime;

public class SessionInfo {
    private final CoverImage coverImage;
    private final SessionDetail sessionDetail;

    public SessionInfo(CoverImage coverImage, String pricingType, int tuitionFee, String sessionStatus, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this(coverImage, new SessionDetail(pricingType, tuitionFee, sessionStatus, capacity, startDate, endDate));
    }
    public SessionInfo(CoverImage coverImage, SessionDetail sessionDetail) {
        this.coverImage = coverImage;
        this.sessionDetail = sessionDetail;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        sessionDetail.sessionStatusCheck();
        sessionDetail.enroll(students, enrollApplicationDTO);
    }

    public Long getImageId() {
        return coverImage.getId();
    }
    public String getPricingType() {
        return sessionDetail.getPricingType();
    }
    public int getTuitionFee() {
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

    @Override
    public String toString() {
        return "SessionInfo{" +
                "coverImage=" + coverImage +
                ", sessionDetail=" + sessionDetail +
                '}';
    }
}
