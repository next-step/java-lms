package nextstep.qna.domain.step2;

import java.time.LocalDate;

public class SessionDate {
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SessionDate(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }
}
