package nextstep.session.domain;

import java.time.LocalDate;

public class SessionSchedule {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionSchedule(LocalDate startDate, LocalDate endDate) {
        validateSchedule(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateSchedule(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료 날짜는 시작 날짜보다 빠를 수 없습니다.");
        }
    }

    public boolean isAbleToRegister(LocalDate registrationDate) {
        return registrationDate.isAfter(startDate) && registrationDate.isBefore(endDate);
    }


}
