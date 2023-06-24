package nextstep.courses.domain;

import nextstep.qna.UnAuthorizedException;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {

    private final long sessionId;
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPeriod(long sessionId, LocalDate startDate, LocalDate endDate) {
        validateInitialize(startDate, endDate);

        this.sessionId = sessionId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateInitialize(LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(startDate)) {
            throw new IllegalArgumentException("강의 시작일이 등록되질 않았어요 :(");
        }

        if (Objects.isNull(endDate)) {
            throw new IllegalArgumentException("강의 종료일이 등록되질 않았어요 :(");
        }
    }

    public SessionPeriod changeStartDate(long sessionId, LocalDate startDate) {
        validateSessionId(sessionId);
        validateChangeStartDate(startDate);
        this.startDate = startDate;
        return this;
    }

    private void validateChangeStartDate(LocalDate startDate) {
        LocalDate now = LocalDate.now();
        if (startDate.isBefore(now)) {
            throw new IllegalArgumentException("강의 시작 날짜가 현재 날짜보다 앞일 수 없습니다 :(");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 시작 날짜가 강의 종료 날짜가 보다 앞일 수 없습니다 :(");
        }
    }

    public SessionPeriod changeEndDate(long sessionId, LocalDate endDate) {
        validateSessionId(sessionId);
        validateChangeEndDate(startDate, endDate);
        this.endDate = endDate;
        return this;
    }

    private void validateChangeEndDate(LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료 날짜가 강의 시작 날짜가 보다 앞일 수 없습니다 :(");
        }
    }

    public int calcDiffStartDateAnd(LocalDate targetDate) {
        return startDate.compareTo(targetDate);
    }

    public boolean isBeforeStartDate(LocalDate now) {
        return now.isBefore(startDate);
    }

    public boolean isAfterEndDate(LocalDate now) {
        return now.isAfter(endDate);
    }

    private void validateSessionId(long sessionId) {
        if (sessionId == 0L) {
            throw new IllegalArgumentException("유효하지 않는 세션 아이디에요 :( [입력 값 : " + sessionId + "]");
        }

        if (sessionId != this.sessionId) {
            throw new UnAuthorizedException("세션 아이디가 동일하지 않아요 :( [입력 값 : " + sessionId + "]");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return sessionId == that.sessionId && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, startDate, endDate);
    }
}
