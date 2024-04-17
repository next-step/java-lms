package nextstep.sessions.domain;

public class SessionPeriod {

    public static final int DATE_LENGTH = 8;
    private final int startDate;
    private final int endDate;

    public SessionPeriod(String startDate, String endDate) {
        this.startDate = validateDate(startDate);
        this.endDate = validateDate(endDate);
    }

    private int validateDate(String date) {
        if (date.length() != DATE_LENGTH) {
            throw new IllegalArgumentException("날짜가 형식에 맞지 않습니다.");
        }
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("강의 시작 및 종료일은 숫자형식이어야 합니다.");
        }
    }
}
