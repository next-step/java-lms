package nextstep.courses.domain.course.session;

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

    public boolean charged() {
        return this.sessionState.charged();
    }

    public boolean notRecruiting() {
        return this.sessionStatus != SessionStatus.ONGOING;
    }

    public boolean endDateIsSameOrAfter(LocalDate date) {
        return duration.endDateIsSameOrAfter(date);
    }

    public void changeOnReady() {
        this.sessionStatus = SessionStatus.READY;
    }

    public void changeOnRecruit() {
        this.sessionStatus = SessionStatus.ONGOING;
    }

    public void changeOnEnd() {
        this.sessionStatus = SessionStatus.END;
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
