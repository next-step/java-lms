package nextstep.courses.domain.startegy.sessionStateStartegy;

import java.time.LocalDate;

@FunctionalInterface
public interface SessionStateStartegy {
    boolean checkStatus(LocalDate startDate, LocalDate endDate, LocalDate currentDate);
}
