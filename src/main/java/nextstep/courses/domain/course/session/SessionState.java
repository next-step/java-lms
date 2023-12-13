package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class SessionState {
    private static final int MAX_APPLY = Integer.MAX_VALUE;

    private SessionType sessionType;

    private Long amount;

    private int quota;

    public SessionState() {
        this.sessionType = SessionType.FREE;
        this.amount = 0L;
        this.quota = MAX_APPLY;
    }

    public SessionState(SessionType sessionType, Long amount, int quota) {
        validate(sessionType, amount, quota);
        this.sessionType = sessionType;
        this.amount = amount;
        this.quota = quota;
    }

    private void validate(SessionType sessionType, Long amount, int quota) {
        checkFreeTypeAmountZero(sessionType, amount);
        checkFreeTypeQuotaMax(sessionType, quota);
    }

    private void checkFreeTypeAmountZero(SessionType sessionType, Long amount) {
        if (sessionType.free() && amount != 0L) {
            throw new IllegalArgumentException("무료 강의는 강의료가 0원이어야 합니다.");
        }
    }

    private void checkFreeTypeQuotaMax(SessionType sessionType, int quota) {
        if (sessionType.free() && quota != MAX_APPLY) {
            throw new IllegalArgumentException("무료 강의는 수강 가능 인원이 최대여야 합니다.");
        }
    }

    public boolean sameAmount(Long amount) {
        return Objects.equals(this.amount, amount);
    }

    public boolean charged() {
        return this.sessionType.charged();
    }

    public boolean chargedAndFull(List<NsUser> applicants) {
        return this.sessionType == SessionType.CHARGE && this.quota == applicants.size();
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public Long getAmount() {
        return amount;
    }

    public int getQuota() {
        return quota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionState that = (SessionState) o;
        return quota == that.quota && sessionType == that.sessionType && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionType, amount, quota);
    }

    @Override
    public String toString() {
        return "SessionState{" +
                "sessionType=" + sessionType +
                ", amount=" + amount +
                ", quota=" + quota +
                '}';
    }
}
