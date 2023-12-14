package nextstep.courses.domain.course.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class SessionDetail {
    private Duration duration;

    private SessionState sessionState;

    private SessionStatus sessionStatus;

    private RecruitStatus recruitStatus;

    public SessionDetail(Duration duration, SessionState sessionState,
                         SessionStatus sessionStatus, RecruitStatus recruitStatus) {
        if (duration == null) {
            throw new IllegalArgumentException("기간이 추가되어야 합니다.");
        }

        if (sessionState == null) {
            throw new IllegalArgumentException("강의 정보가 추가되어야 합니다.");
        }

        if (sessionStatus == null) {
            throw new IllegalArgumentException("강의 현황 상태가 추가되어야 합니다.");
        }

        if (recruitStatus == null) {
            throw new IllegalArgumentException("강의 모집 여부가 추가되어야 합니다.");
        }

        this.duration = duration;
        this.sessionState = sessionState;
        this.sessionStatus = sessionStatus;
        this.recruitStatus = recruitStatus;
    }

    public Duration getDuration() {
        return duration;
    }

    public SessionState getSessionState() {
        return sessionState;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public RecruitStatus getRecruitStatus() {
        return recruitStatus;
    }

    public boolean sameAmount(Long amount) {
        return this.sessionState.sameAmount(amount);
    }

    public void checkApplyPossible() {
        checkStatusOnRecruit();
        checkStatusOnReadyOrOnGoing();
    }

    private void checkStatusOnRecruit() {
        if (this.notRecruiting()) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능 합니다.");
        }
    }

    private void checkStatusOnReadyOrOnGoing() {
        if (this.notReadyOrOnGoing()) {
            throw new IllegalArgumentException("강의 신청은 준비, 진행중일 때만 가능 합니다.");
        }
    }

    public boolean notRecruiting() {
        return this.recruitStatus == RecruitStatus.NOT_RECRUIT;
    }

    public boolean notReadyOrOnGoing() {
        return this.sessionStatus == SessionStatus.END;
    }

    public void checkPaymentIsPaid(NsUser loginUser, Payment payment) {
        if(isNotPaid(payment)) {

        }
    }

    public boolean isNotPaid(Payment payment) {
        return payment == null || !charged();
    }

    public boolean charged() {
        return this.sessionState.charged();
    }

    public boolean endDateIsSameOrAfter(LocalDate date) {
        return duration.endDateIsSameOrAfter(date);
    }

    public void changeOnReady(LocalDate date) {
        checkStartDateIsSameOrBefore(date);
        this.sessionStatus = SessionStatus.READY;
    }

    public void changeOnRecruit(LocalDate date) {
        checkStartDateIsSameOrBefore(date);
        this.sessionStatus = SessionStatus.ONGOING;
    }

    private void checkStartDateIsSameOrBefore(LocalDate date) {
        if (startDateIsSameOrBefore(date)) {
            throw new IllegalArgumentException("강의 시작일 이전에 변경 가능합니다.");
        }
    }

    public void changeOnEnd(LocalDate date) {
        checkEndDateIsSameOrAfter(date);
        this.sessionStatus = SessionStatus.END;
    }

    private void checkEndDateIsSameOrAfter(LocalDate date) {
        if (endDateIsSameOrAfter(date)) {
            throw new IllegalArgumentException("강의 종료일 이후 변경 가능합니다.");
        }
    }

    public boolean startDateIsSameOrBefore(LocalDate date) {
        return this.duration.startDateIsSameOrBefore(date);
    }

    public void setSessionState(SessionState sessionState) {
        this.sessionState = sessionState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDetail that = (SessionDetail) o;
        return Objects.equals(duration, that.duration) && Objects.equals(sessionState, that.sessionState) && sessionStatus == that.sessionStatus && recruitStatus == that.recruitStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, sessionState, sessionStatus, recruitStatus);
    }

    @Override
    public String toString() {
        return "SessionDetail{" +
                "duration=" + duration +
                ", sessionState=" + sessionState +
                ", sessionStatus=" + sessionStatus +
                ", recruitStatus=" + recruitStatus +
                '}';
    }
}
