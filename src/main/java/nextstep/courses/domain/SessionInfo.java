package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import java.time.LocalDateTime;

public class SessionInfo {
    private final SessionBasicInfo basicInfo;
    private final SessionDetailInfo detailInfo;

    public SessionInfo(String title, SessionStatus status, SessionImage image, 
                      LocalDateTime startDate, LocalDateTime endDate, 
                      SessionType type, int price) {
        this.basicInfo = new SessionBasicInfo(title, status, image);
        this.detailInfo = new SessionDetailInfo(startDate, endDate, type, price);
    }

    public boolean isRecruiting() {
        return basicInfo.isRecruiting();
    }

    public boolean isPaid() {
        return detailInfo.isPaid();
    }

    public void validatePayment(Payment payment) {
        detailInfo.validatePayment(payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return basicInfo.equals(that.basicInfo) && detailInfo.equals(that.detailInfo);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(basicInfo, detailInfo);
    }
} 