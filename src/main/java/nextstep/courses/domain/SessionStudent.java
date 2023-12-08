package nextstep.courses.domain;

public class SessionStudent {

    private int maxStudentCount;

    private SessionStudents sessionStudents;

    public SessionStudent(int maxStudentCount, SessionStudents sessionStudents) {
        this.maxStudentCount = maxStudentCount;
        this.sessionStudents = sessionStudents;
    }

    public boolean isUnderMaxStudentCount() {
        if (sessionStudents.enrolledStudentsCount() >= maxStudentCount) {
            throw new IllegalArgumentException("수강 인원이 다 찼습니다");
        }
        return sessionStudents.enrolledStudentsCount() < maxStudentCount;
    }
}
