package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionPeriodFixture {
    private static final long SESSION_MONTH_TERM = 3L;

    public static SessionPeriod 과거기간() {
        LocalDate agoYear = LocalDate.now().minusYears(1L);
        return new SessionPeriod(agoYear, agoYear.plusMonths(SESSION_MONTH_TERM));
    }

    public static SessionPeriod 미래기간() {
        LocalDate afterYear = LocalDate.now().plusYears(1L);
        return new SessionPeriod(afterYear, afterYear.plusMonths(SESSION_MONTH_TERM));
    }

    public static SessionPeriod 현재기간_포함() {
        LocalDate now = LocalDate.now();
        return new SessionPeriod(now.minusMonths(3L), now.plusMonths(3L));
    }
}
