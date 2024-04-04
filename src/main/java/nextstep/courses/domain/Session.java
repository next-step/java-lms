package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Session {

    private final Period period;

    public Session(LocalDate startDate, LocalDate endDate) {
        this.period = new Period(startDate, endDate);
    }

    public LocalDate startDate() {
        return period.startDate();
    }

    public LocalDate endDate() {
        return period.endDate();
    }

    public class Period {
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Period(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public LocalDate startDate() {
            return startDate;
        }

        public LocalDate endDate() {
            return endDate;
        }

    }
}
