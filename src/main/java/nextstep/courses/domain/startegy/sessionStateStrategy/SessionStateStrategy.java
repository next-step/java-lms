package nextstep.courses.domain.startegy.sessionStateStrategy;

import java.time.LocalDate;

@FunctionalInterface
public interface SessionStateStrategy {
    boolean checkStatus(LocalDate startDate, LocalDate endDate, LocalDate currentDate);
}
