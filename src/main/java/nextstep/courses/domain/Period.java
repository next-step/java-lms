package nextstep.courses.domain;

import java.util.Date;

public class Period {

    private Date startDate;

    private Date endDate;

    public Period() {
    }

    public Period(Date startDate, Date endDate) {
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 먼저일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
}
