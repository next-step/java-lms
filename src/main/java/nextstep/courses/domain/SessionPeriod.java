package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;

import java.time.LocalDate;

public class SessionPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException{
        if(endDate.compareTo(startDate) <= 0) {
            throw new IllegalArgumentException("기간을 확인해 주세요");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

//    private void validStartDate() throws CannotRegisterSessionException {
//        LocalDate now = LocalDate.now();
//        if (now.isAfter(this.startDate)) {
//            throw new CannotRegisterSessionException("이미 시작한 강의입니다.");
//        }
//    }

}
