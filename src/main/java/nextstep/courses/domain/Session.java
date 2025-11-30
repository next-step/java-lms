package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final SessionImage coverImage;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 이후여야 한다");
        }
    }
}
