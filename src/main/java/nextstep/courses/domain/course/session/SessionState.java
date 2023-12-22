package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.Apply;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionState {
    private static final int MAX_APPLY = Integer.MAX_VALUE;

    private SessionType sessionType;

    private Long amount;

    private int quota;

    private Applies applies;

    public SessionState() {
        this.sessionType = SessionType.FREE;
        this.amount = 0L;
        this.quota = MAX_APPLY;
        this.applies = new Applies();
    }

    public SessionState(SessionType sessionType, Long amount, int quota, Applies applies) {
        if (applies == null) {
            throw new IllegalArgumentException("수강생은 빈 값이면 안됩니다.");
        }
        validate(sessionType, amount, quota);
        this.sessionType = sessionType;
        this.amount = amount;
        this.quota = quota;
        this.applies = applies;
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
            throw new IllegalArgumentException("유료강의는 0원보다 크고 정원 수가 0보다 커야 합니다.");
        }
    }

    public Apply addApply(Long sessionId, Long nsUserId, LocalDateTime date) {
        checkChargedAndApplySizeIsValid();

        return this.applies.addApply(sessionId, nsUserId, date);
    }

    private void checkChargedAndApplySizeIsValid() {
        if(chargedAndFull()) {
            throw new IllegalArgumentException("수강 신청 인원이 초과 되었습니다.");
        }
    }

    public Apply approve(Apply apply, LocalDateTime date) {
        return this.applies.approve(apply, date);
    }

    public Apply cancel(Apply apply, LocalDateTime date) {
        return this.applies.cancel(apply, date);
    }

    private boolean chargedAndFull() {
        return this.charged() && applySizeFull();
    }

    public boolean charged() {
        return this.sessionType.charged();
    }

    private boolean applySizeFull() {
        return this.quota == applies.size();
    }

    public int size() {
        return this.applies.size();
    }

    public boolean sameAmount(Long amount) {
        return Objects.equals(this.amount, amount);
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

    public Applies getApplies() {
        return applies;
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
                ", applies=" + applies +
                '}';
    }
}
