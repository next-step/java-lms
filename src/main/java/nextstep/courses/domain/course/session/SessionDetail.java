package nextstep.courses.domain.course.session;

import java.time.LocalDate;
import java.util.Objects;

public class SessionDetail {
    private SessionDuration sessionDuration;

    private SessionState sessionState;

    private SessionProgressStatus sessionProgressStatus;

    private SessionRecruitStatus sessionRecruitStatus;

    public SessionDetail(SessionDuration sessionDuration, SessionState sessionState,
                         SessionProgressStatus sessionProgressStatus, SessionRecruitStatus sessionRecruitStatus) {
        if (sessionDuration == null) {
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

        this.sessionDuration = sessionDuration;
        this.sessionState = sessionState;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
    }

    public SessionDuration getDuration() {
        return sessionDuration;
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

    public void changeOnReady(LocalDate date) {
        checkChangeDateIsSameOrAfterWithEndDate(date);
        this.sessionProgressStatus = SessionProgressStatus.READY;
    }

    public void changeOnGoing(LocalDate date) {
        checkChangeDateIsSameOrAfterWithEndDate(date);
        this.sessionProgressStatus = SessionProgressStatus.ONGOING;
    }

    private void checkChangeDateIsSameOrAfterWithEndDate(LocalDate date) {
        if (this.sessionDuration.changeDateIsSameOrAfterWithEndDate(date)) {
            throw new IllegalArgumentException("강의 종료일 이전에 변경 가능 합니다.");
        }
    }

    public void changeOnEnd(LocalDate date) {
        checkChangeDateIsBeforeOrSameWithEndDate(date);
        this.sessionProgressStatus = SessionProgressStatus.END;
    }

    private void checkChangeDateIsBeforeOrSameWithEndDate(LocalDate date) {
        if (this.sessionDuration.changeDateIsBeforeOrSameWithEndDate(date)) {
            throw new IllegalArgumentException("강의 종료일 이후에 변경 가능합니다.");
        }
    }

    public void changeSessionState(SessionState sessionState) {
        this.sessionState = sessionState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDetail that = (SessionDetail) o;
        return Objects.equals(sessionDuration, that.sessionDuration) && Objects.equals(sessionState, that.sessionState) && sessionProgressStatus == that.sessionProgressStatus && sessionRecruitStatus == that.sessionRecruitStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionDuration, sessionState, sessionProgressStatus, sessionRecruitStatus);
    }

    @Override
    public String toString() {
        return "SessionDetail{" +
                "duration=" + sessionDuration +
                ", sessionState=" + sessionState +
                ", sessionStatus=" + sessionProgressStatus +
                ", recruitStatus=" + sessionRecruitStatus +
                '}';
    }
}
