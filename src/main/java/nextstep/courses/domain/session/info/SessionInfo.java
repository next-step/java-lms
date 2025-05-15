package nextstep.courses.domain.session.info;

import lombok.Builder;
import lombok.Getter;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.payments.domain.Payment;

@Getter
@Builder(toBuilder = true)
public class SessionInfo {
    private final SessionBasicInfo basicInfo;
    private final SessionDetailInfo detailInfo;
    private final int maxEnrollment;
    private final SessionProgressStatus progressStatus;
    private final SessionRecruitmentStatus recruitmentStatus;

    public SessionInfo(SessionBasicInfo basicInfo, SessionDetailInfo detailInfo, int maxEnrollment) {
        this(basicInfo, detailInfo, maxEnrollment, SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
    }

    public SessionInfo(SessionBasicInfo basicInfo, SessionDetailInfo detailInfo, int maxEnrollment,
                      SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        this.basicInfo = basicInfo;
        this.detailInfo = detailInfo;
        this.maxEnrollment = maxEnrollment;
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
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
        return basicInfo.equals(that.basicInfo) && 
               detailInfo.equals(that.detailInfo) && 
               maxEnrollment == that.maxEnrollment &&
               progressStatus == that.progressStatus &&
               recruitmentStatus == that.recruitmentStatus;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(basicInfo, detailInfo, maxEnrollment, progressStatus, recruitmentStatus);
    }
} 