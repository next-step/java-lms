package nextstep.courses.domain;

public class SessionRegistration {

    private static final String REGISTER_ERROR_MESSAGE = "수강 신청은 모집중 일때만 신청 가능합니다.";
    private static final String MAX_STUDENTS_COUNT_ERROR_MESSAGE = "최대 수강 신청 인원을 초과할 수 없습니다.";

    private final int maxStudentCount;
    private int currentStudentCount;
    private SessionStatusType sessionStatusType;

    public SessionRegistration(int maxStudentCount, int currentStudentCount, SessionStatusType sessionStatusType) {
        this.maxStudentCount = maxStudentCount;
        this.currentStudentCount = currentStudentCount;
        this.sessionStatusType = sessionStatusType;
    }

    public void register(int studentCount) {
        this.validationSessionStatus();
        this.validationSessionMaxStudentCount(studentCount);

        this.currentStudentCount += studentCount;
    }

    private void validationSessionStatus() {
        if (this.sessionStatusType != SessionStatusType.RECRUITING) {
            throw new IllegalArgumentException(REGISTER_ERROR_MESSAGE);
        }
    }

    private void validationSessionMaxStudentCount(int studentCount) {
        if (this.currentStudentCount + studentCount > this.maxStudentCount) {
            throw new IllegalArgumentException(MAX_STUDENTS_COUNT_ERROR_MESSAGE);
        }
    }
}
