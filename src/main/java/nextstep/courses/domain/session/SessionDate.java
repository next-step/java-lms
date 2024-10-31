package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionDate {
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
