package nextstep.courses.domain.session;

public class Enrollment {
    private final Students students;
    private final int fee;
    private final int maxStudents;
    private SessionStatus sessionStatus;

    public Enrollment(int fee, int maxStudents, SessionStatus sessionStatus) {
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.students = new Students();
        this.sessionStatus = sessionStatus;
    }

    public Enrollment(int fee, int maxStudents, Students students, SessionStatus sessionStatus) {
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.students = students;
        this.sessionStatus = sessionStatus;
    }

    public void enroll(Student newStudent) {
        if (!sessionStatus.isApplying()) {
            throw new RuntimeException("수강 신청은 강의 상태가 모집중이 아니면 불가능합니다. 강의 상태 : " + sessionStatus);
        }

        if (isPaySession()) {
            newStudent.isPaid(fee);
            isMaxStudents();
        }

        this.students.add(newStudent);
    }

    private boolean isPaySession() {
        return this.fee > 0;
    }

    private void isMaxStudents() {
        if (this.students.getCounts() >= maxStudents) {
            throw new RuntimeException("유료 강의는 최대 수강인원을 초과할 수 없습니다. 최대수강인원 : " + maxStudents);
        }
    }

    public void changeStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
