package nextstep.courses.domain;

public class PaidSession extends Session {
    private static final int PAID_SESSION_TUITION_FEE = 10000;
    private static final int MAX_SESSION_STUDENT_COUNT = 5;

    public PaidSession(int tuition) {
        super(tuition);
        validateTuitionFee(tuition);
    }

    public PaidSession(SessionStatus sessionStatus) {
        super(sessionStatus);
    }

    public PaidSession(int studentCount, int tuition) {
        super(studentCount, tuition);
        validateStudentCount(studentCount);
    }

    private void validateTuitionFee(int tuition) {
        if (tuition != PAID_SESSION_TUITION_FEE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateStudentCount(int studentCount) {
        if (studentCount > MAX_SESSION_STUDENT_COUNT) {
            throw new IllegalArgumentException();
        }
    }
}
