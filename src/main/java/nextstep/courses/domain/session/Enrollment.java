package nextstep.courses.domain.session;

public class Enrollment {
    private final Students students;
    private final int fee;
    private final int maxStudents;
    private SessionProgressStatus sessionProgressStatus;
    private SessionApplyStatus sessionApplyStatus;

    public Enrollment(int fee, int maxStudents, SessionProgressStatus sessionProgressStatus, SessionApplyStatus sessionApplyStatus) {
        this(fee, maxStudents, new Students(), sessionProgressStatus, sessionApplyStatus);
    }

    public Enrollment(int fee, int maxStudents, Students students, SessionProgressStatus sessionProgressStatus, SessionApplyStatus sessionApplyStatus) {
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.students = students;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionApplyStatus = sessionApplyStatus;
    }

    public void enroll(Student newStudent) {
        if (!isAbleToEnroll()) {
            throw new RuntimeException("수강 신청은 강의 상태가 모집중이 아니면 불가능합니다. 강의 상태 : " + sessionProgressStatus);
        }

        if (!newStudent.getIsSelected()) {
            throw new RuntimeException("선발되지 않은 사람은 수강을 취소합니다.");
        }

        if (isPaySession()) {
            newStudent.isPaid(fee);
            isMaxStudents();
        }

        this.students.add(newStudent);
    }

    public void changeProgressStatus(SessionProgressStatus sessionProgressStatus) {
        this.sessionProgressStatus = sessionProgressStatus;
    }

    public void changeApplyStatus(SessionApplyStatus sessionApplyStatus) {
        this.sessionApplyStatus = sessionApplyStatus;
    }

    public Students getStudents() {
        return students;
    }

    private boolean isPaySession() {
        return this.fee > 0;
    }

    private void isMaxStudents() {
        if (this.students.getCounts() >= maxStudents) {
            throw new RuntimeException("유료 강의는 최대 수강인원을 초과할 수 없습니다. 최대수강인원 : " + maxStudents);
        }
    }

    private boolean isAbleToEnroll() {
        return this.sessionProgressStatus.isAbleToApply() && this.sessionApplyStatus.isApplying();
    }
}
