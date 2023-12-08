package nextstep.session.domain;

import java.time.LocalDate;

public class CurrentPeriodStrategy implements PeriodStrategy {
    @Override
    public LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
