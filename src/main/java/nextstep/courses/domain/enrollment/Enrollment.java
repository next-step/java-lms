package nextstep.courses.domain.enrollment;

public class Enrollment {

    private final Students students;

    public Enrollment(int maxEnrollment) {
        this.students = new Students(maxEnrollment);
    }

    public int sessionCapacity() {
        return students.sessionCapacity();
    }

    public int currentEnrolmentCount() {
        return students.countEnrollment();
    }

    public boolean hasEnrolledStudent() {
        return !students.isEmpty();
    }

    public void enroll(Student student) {
        students.enroll(student);
    }
}
