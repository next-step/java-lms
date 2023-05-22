package lms.domain;

import java.time.LocalDate;

public class Session {

    private LocalDate startDate;
    private LocalDate endDate;

    public Session(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
