package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class SessionStudent {

    private int maxStudentCount;

    private SessionStudents sessionStudents;

    public SessionStudent(int maxStudentCount, SessionStudents sessionStudents) {
        this.maxStudentCount = maxStudentCount;
        this.sessionStudents = sessionStudents;
    }

    public void isUnderMaxStudentCount() {
        if (sessionStudents.enrolledStudentsCount() >= maxStudentCount) {
            throw new IllegalArgumentException("수강 인원이 다 찼습니다");
        }
    }

    public void add(NsUser student) {
        sessionStudents.add(student);
    }
}
