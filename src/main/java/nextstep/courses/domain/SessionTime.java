package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionDateTimeException;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionTime {
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;

    public SessionTime(LocalDateTime openingDateTime, LocalDateTime closingDateTime) {
        checkDateTime(openingDateTime, closingDateTime);
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
    }

    public LocalDateTime getOpeningDateTime() {
        return openingDateTime;
    }

    public LocalDateTime getClosingDateTime() {
        return closingDateTime;
    }

    private static void checkDateTime(LocalDateTime openingDateTime, LocalDateTime closingDateTime) throws InvalidSessionDateTimeException {
        if (openingDateTime.isAfter(closingDateTime)) {
            throw new InvalidSessionDateTimeException("강의 시작 시간은 종료 시간보다 늦을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionTime that = (SessionTime) o;
        return Objects.equals(openingDateTime, that.openingDateTime) && Objects.equals(closingDateTime, that.closingDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openingDateTime, closingDateTime);
    }
}
