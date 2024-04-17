package nextstep.courses.domain.session;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "시작일은 널값일 수 없습니다. ");
        Assert.notNull(endDate, "종료일은 널값일 수 없습니다. ");
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public boolean isEnd(LocalDate date) {
        if (date == null) {
            return true;
        }
        return this.endDate.equals(date) || this.endDate.isBefore(date);
    }
}
