package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class SessionStudent {
    private final Students students;
    private int maxStudentCount;

    public SessionStudent(Students students, int maxStudentCount) {
        this.students = students;
        this.maxStudentCount = maxStudentCount;
    }

    public SessionStudent(int maxStudentCount) {
        this.students = new Students();
        this.maxStudentCount = maxStudentCount;
    }

    public SessionStudent(Students students) {
        this.students = students;
    }

    public void add(NsUser student) {
        this.students.add(student);
    }

    public boolean isMaxStudents() {
        return this.students.totalCount() == this.maxStudentCount;
    }

    public int studentCount() {
        return this.students.totalCount();
    }
}
