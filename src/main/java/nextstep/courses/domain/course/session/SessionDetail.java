package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Apply;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class SessionDetail {
    private Duration duration;

    private SessionState sessionState;

    private SessionProgressStatus sessionProgressStatus;

    private SessionRecruitStatus sessionRecruitStatus;

    public SessionDetail(Duration duration, SessionState sessionState,
                         SessionProgressStatus sessionProgressStatus, SessionRecruitStatus sessionRecruitStatus) {
        if (duration == null) {
            throw new IllegalArgumentException("기간이 추가되어야 합니다.");
        }

        if (sessionState == null) {
            throw new IllegalArgumentException("강의 정보가 추가되어야 합니다.");
        }

        if (sessionProgressStatus == null) {
            throw new IllegalArgumentException("강의 현황 상태가 추가되어야 합니다.");
        }

        if (sessionRecruitStatus == null) {
            throw new IllegalArgumentException("강의 모집 여부가 추가되어야 합니다.");
        }

        this.duration = duration;
        this.sessionState = sessionState;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
    }

    public Duration getDuration() {
        return duration;
    }

    public SessionState getSessionState() {
        return sessionState;
    }

    public SessionProgressStatus getSessionStatus() {
        return sessionProgressStatus;
    }

    public SessionRecruitStatus getRecruitStatus() {
        return sessionRecruitStatus;
    }

    public int size() {
        return this.sessionState.size();
    }

    public boolean sameAmount(Long amount) {
        return this.sessionState.sameAmount(amount);
    }

    public Apply addApply(Long sessionId, Long nsUserId, LocalDateTime date) {
        checkStatusOnRecruit();
        checkStatusOnReadyOrOnGoing();

        return this.sessionState.addApply(sessionId, nsUserId, date);
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
        return this.sessionRecruitStatus.notRecruiting();
    }

    public boolean notReadyOrOnGoing() {
        return this.sessionProgressStatus.notReadyOrOnGoing();
    }

    public boolean charged() {
        return this.sessionState.charged();
    }

    public void changeOnReady(LocalDate date) {
        checkChangeDateIsSameOrAfterWithEndDate(date);
        this.sessionProgressStatus = SessionProgressStatus.READY;
    }

    public void changeOnGoing(LocalDate date) {
        checkChangeDateIsSameOrAfterWithEndDate(date);
        this.sessionProgressStatus = SessionProgressStatus.ONGOING;
    }

    private void checkChangeDateIsSameOrAfterWithEndDate(LocalDate date) {
        if (this.duration.changeDateIsSameOrAfterWithEndDate(date)) {
            throw new IllegalArgumentException("강의 종료일 이전에 변경 가능 합니다.");
        }
    }

    public void changeOnEnd(LocalDate date) {
        checkChangeDateIsBeforeOrSameWithEndDate(date);
        this.sessionProgressStatus = SessionProgressStatus.END;
    }

    private void checkChangeDateIsBeforeOrSameWithEndDate(LocalDate date) {
        if (this.duration.changeDateIsBeforeOrSameWithEndDate(date)) {
            throw new IllegalArgumentException("강의 종료일 이후에 변경 가능합니다.");
        }
    }

    public void changeSessionState(SessionState sessionState) {
        this.sessionState = sessionState;
    }

    public Apply approve(Apply apply, LocalDateTime date) {
        return this.sessionState.approve(apply, date);
    }

    public Apply cancel(Apply apply, LocalDateTime date) {
        return this.sessionState.cancel(apply, date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDetail that = (SessionDetail) o;
        return Objects.equals(duration, that.duration) && Objects.equals(sessionState, that.sessionState) && sessionProgressStatus == that.sessionProgressStatus && sessionRecruitStatus == that.sessionRecruitStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, sessionState, sessionProgressStatus, sessionRecruitStatus);
    }

    @Override
    public String toString() {
        return "SessionDetail{" +
                "duration=" + duration +
                ", sessionState=" + sessionState +
                ", sessionStatus=" + sessionProgressStatus +
                ", recruitStatus=" + sessionRecruitStatus +
                '}';
    }
}
