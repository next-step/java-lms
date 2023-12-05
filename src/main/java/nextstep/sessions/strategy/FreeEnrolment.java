package nextstep.sessions.strategy;

public class FreeEnrolment implements EnrolmentStrategy{
    public static final int INIT_COUNT = 0;
    private int studentCount;

    public FreeEnrolment() {
        this.studentCount = INIT_COUNT;
    }

    @Override
    public void enrolment(int payMoney) {
        this.studentCount++;
    }
}
