package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Duration {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Duration(LocalDateTime startDate, LocalDateTime endDate) {
        inputValidation(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void inputValidation(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 빠를 수 없습니다.");
        }
    }

}
