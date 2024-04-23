package nextstep.courses.domain.session;

public class Enrollment {
    private final Students students;
    private final int fee;
    private final int maxStudents;

    public Enrollment(int fee, int maxStudents) {
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.students = new Students();
    }

    public Enrollment(int fee, int maxStudents, Students students) {
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.students = students;
    }

    public void enroll(Student newStudent) {
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
}
