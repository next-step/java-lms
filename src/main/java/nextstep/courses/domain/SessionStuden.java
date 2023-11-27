package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class SessionStuden {
    private final Students students;
    private int maxStudentCount;

    public SessionStuden(Students students, int maxStudentCount) {
        this.students = students;
        this.maxStudentCount = maxStudentCount;
    }

    public SessionStuden(int maxStudentCount) {
        this.students = new Students();
        this.maxStudentCount = maxStudentCount;
    }

    public SessionStuden(Students students) {
        this.students = students;
    }

    public void add(NsUser student) {
        this.students.add(student);
    }

    public boolean isMaxStudents() {
        return this.students.totalCount() == this.maxStudentCount;
    }
}
