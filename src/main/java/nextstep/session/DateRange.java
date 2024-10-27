package nextstep.session;

import java.util.Date;

public class DateRange {

    private static final String DATE_MESSAGE = "종료일이 시작일보다 빠릅니다.";

    private final Date startDate;
    private final Date endDate;

    public DateRange(Date startDate, Date endDate) {
        confirmDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void confirmDate(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException(DATE_MESSAGE);
        }
    }
}
