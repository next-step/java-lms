package nextstep.courses.domain;

public class PaySession extends Session {

    private final int maxCountOfStudents;

    public PaySession(Image image, Period period, int maxCountOfStudents) {
        super(image, period, SessionType.PAY);
        this.maxCountOfStudents = maxCountOfStudents;
    }

    public int getMaxCountOfStudents() {
        return this.maxCountOfStudents;
    }
}
