package nextstep.courses.domain.course.session;

import java.util.Objects;

public class SessionState {
    private static final int MAX_APPLY = Integer.MAX_VALUE;

    private final SessionType sessionType;

    private final Long amount;

    private final int quota;

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
        if (sessionType.free()) {
            checkTypeisFree(amount, quota);
        }

        if (sessionType.charged()) {
            checkTypeisCharged(amount, quota);
        }
    }

    private void checkTypeisFree(Long amount, int quota) {
        if(amount != 0L || quota != MAX_APPLY) {
            throw new IllegalArgumentException("무료 강의는 0원, 정원 수가 최대여야 합니다.");
        }
    }

    private void checkTypeisCharged(Long amount, int quota) {
        if(amount == 0L || quota == 0) {
            throw new IllegalArgumentException("유료 강의는 0원보다 크고 정원 수가 0보다 커야 합니다.");
        }
    }

    public SessionType sessionType() {
        return sessionType;
    }

    public Long amount() {
        return amount;
    }

    public int quota() {
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
