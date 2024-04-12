package nextstep.sessions.domain;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        Assert.notNull(start, "시작일은 필수 데이터입니다.");
        Assert.notNull(end, "종료일은 필수 데이터입니다.");
        assertEndDateAfterStart(start, end);

        this.start = start;
        this.end = end;
    }

    private void assertEndDateAfterStart(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("시작일은 종료일 이전이어야 합니다.");
        }
    }

    public boolean isAfterStartDate(LocalDate targetDate) {
        return start.equals(targetDate) || start.isBefore(targetDate);
    }
}
