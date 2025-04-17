package nextstep.courses.domain.session.info;

import lombok.Getter;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.payments.domain.Payment;

@Getter
public class SessionInfo {
    private final SessionBasicInfo basicInfo;
    private final SessionDetailInfo detailInfo;

    public SessionInfo(SessionBasicInfo basicInfo, SessionDetailInfo detailInfo) {
        this.basicInfo = basicInfo;
        this.detailInfo = detailInfo;
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