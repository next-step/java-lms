package nextstep.sessions.domain;

import java.util.Date;

public class SessionPeriod {
    private Date startDate;
    private Date endDate;

    public SessionPeriod(Date startDate, Date endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("시작일은 종료일보다 이전이어야 합니다.");
        }
    }
}
