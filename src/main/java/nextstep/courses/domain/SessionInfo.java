package nextstep.courses.domain;

public class SessionInfo {
    private LectureStatus lectureStatus;
    private Students students;
    private final int maxUser;

    public SessionInfo(LectureStatus lectureStatus, Students students, int maxUser) {
        this.lectureStatus = lectureStatus;
        this.students = students;
        this.maxUser = maxUser;
    }

    public Student register(Student student) {
        if (!this.lectureStatus.isRecruiting()) {
            throw new RuntimeException("수강신청은 모집중일때 가능합니다.");
        }
        students.add(student, maxUser);
        return student;
    }


    public LectureStatus getLectureStatus() {
        return lectureStatus;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public void recruiting() {
        this.lectureStatus = LectureStatus.RECRUITING;
    }

    public int currentStudentCount() {
        return students.size();
    }
}
