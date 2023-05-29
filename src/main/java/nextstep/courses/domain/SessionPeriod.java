package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {

    private final long sessionId;
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPeriod(long sessionId, LocalDate startDate, LocalDate endDate) {
        this.sessionId = sessionId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SessionPeriod changeStartDate(LocalDate now, LocalDate startDate) {
        validateDate(startDate, this.endDate, now);
        this.startDate = startDate;
        return this;
    }

    public SessionPeriod changeEndDate(LocalDate now, LocalDate endDate) {
        validateDate(startDate, endDate, now);
        this.endDate = endDate;
        return this;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate, LocalDate now) {
        if (Objects.isNull(startDate)) {
            throw new IllegalArgumentException("강의 시작일이 등록되질 않았어요 :(");
        }

        if (Objects.isNull(endDate)) {
            throw new IllegalArgumentException("강의 종료일이 등록되질 않았어요 :(");
        }

        if (startDate.isBefore(now)) {
            throw new IllegalArgumentException("강의 시작 날짜가 현재 날짜보다 앞일 수 없습니다 :(");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료 날짜가 강의 시작 날짜보다 앞일 수 없습니다 :(");
        }
    }

}
