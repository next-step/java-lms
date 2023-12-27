package nextstep.courses.domain.course.session;

import java.time.LocalDate;
import java.util.Objects;

public class SessionDetail {
    private final SessionDuration sessionDuration;

    private final SessionState sessionState;

    private final SessionProgressStatus sessionProgressStatus;

    private final SessionRecruitStatus sessionRecruitStatus;

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

    public SessionDuration sessionDuration() {
        return sessionDuration;
    }

    public SessionState sessionState() {
        return sessionState;
    }

    public SessionProgressStatus sessionProgressStatus() {
        return sessionProgressStatus;
    }

    public SessionRecruitStatus sessionRecruitStatus() {
        return sessionRecruitStatus;
    }

    public SessionDetail changeOnReady(LocalDate date) {
        checkChangeDateIsSameOrAfterWithEndDate(date);
        return new SessionDetail(sessionDuration, sessionState, SessionProgressStatus.READY, sessionRecruitStatus);
    }

    public SessionDetail changeOnGoing(LocalDate date) {
        checkChangeDateIsSameOrAfterWithEndDate(date);
        return new SessionDetail(sessionDuration, sessionState, SessionProgressStatus.ONGOING, sessionRecruitStatus);
    }

    private void checkChangeDateIsSameOrAfterWithEndDate(LocalDate date) {
        if (this.sessionDuration.changeDateIsSameOrAfterWithEndDate(date)) {
            throw new IllegalArgumentException("강의 종료일 이전에 변경 가능 합니다.");
        }
    }

    public SessionDetail changeOnEnd(LocalDate date) {
        checkChangeDateIsBeforeOrSameWithEndDate(date);
        return new SessionDetail(sessionDuration, sessionState, SessionProgressStatus.END, sessionRecruitStatus);
    }

    private void checkChangeDateIsBeforeOrSameWithEndDate(LocalDate date) {
        if (this.sessionDuration.changeDateIsBeforeOrSameWithEndDate(date)) {
            throw new IllegalArgumentException("강의 종료일 이후에 변경 가능합니다.");
        }
    }

    public boolean charged() {
        return this.sessionState.sessionType().charged();
    }

    public Long getAmount() {
        return this.sessionState.amount();
    }

    public boolean notRecruiting() {
        return this.sessionRecruitStatus.notRecruiting();
    }

    public boolean notReadyOrOnGoing() {
        return this.sessionProgressStatus.notReadyOrOnGoing();
    }

    public boolean chargedAndFull(int applySize) {
        return this.charged() && applySizeFull(applySize);
    }

    private boolean applySizeFull(int applySize) {
        return this.sessionState.quota() == applySize;
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
                "sessionDuration=" + sessionDuration +
                ", sessionState=" + sessionState +
                ", sessionProgressStatus=" + sessionProgressStatus +
                ", sessionRecruitStatus=" + sessionRecruitStatus +
                '}';
    }
}
