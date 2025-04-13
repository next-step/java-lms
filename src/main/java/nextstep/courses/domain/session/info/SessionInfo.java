package nextstep.courses.domain.session.info;

import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionInfo {
    private final SessionBasicInfo basicInfo;
    private final SessionDetailInfo detailInfo;

    public SessionInfo(String title, SessionThumbnail thumbnail,
                       LocalDateTime startDate, LocalDateTime endDate,
                       SessionType type, int price) {
        this.basicInfo = new SessionBasicInfo(title, thumbnail);
        this.detailInfo = new SessionDetailInfo(startDate, endDate, type, price);
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