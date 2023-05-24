package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("수강 시작일(" + startDate + ")은 수강 종료일(" + endDate + ") 이후일 수 없습니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
