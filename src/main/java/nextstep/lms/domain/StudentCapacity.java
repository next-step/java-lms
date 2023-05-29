package nextstep.lms.domain;

public class StudentCapacity {

    private static final int INIT_STUDENT_NUMBER = 0;

    private int studentCapacity;
    private int registeredStudent;

    public StudentCapacity(int studentCapacity, int registeredStudent) {
        this.studentCapacity = studentCapacity;
        this.registeredStudent = registeredStudent;
    }

    public static StudentCapacity init(int studentCapacity) {
        return new StudentCapacity(studentCapacity, INIT_STUDENT_NUMBER);
    }

    public int enroll() {
        registeredStudent++;
        validateStudentCapacity();
        return registeredStudent;
    }

    private void validateStudentCapacity() {
        if (registeredStudent > studentCapacity) {
            throw new IllegalArgumentException("수강 인원이 다 찼습니다.");
        }
    }

    public int cancel() {
        return --registeredStudent;
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    public int getRegisteredStudent() {
        return registeredStudent;
    }
}
