package nextstep.session.domain;

import java.sql.Timestamp;

public class SessionDate {
    private Timestamp startDate;
    private Timestamp endDate;

    public SessionDate(Timestamp startDate, Timestamp endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        checkDate();
    }

    public SessionDate(String startDate, String endDate) {
        this(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
    }

    private void checkDate() {
        if (this.startDate.after(this.endDate)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }
    }
}
