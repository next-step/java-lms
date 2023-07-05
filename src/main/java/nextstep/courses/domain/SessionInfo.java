package nextstep.courses.domain;

public class SessionInfo {
    private final LectureStatus lectureStatus;
    private final Students students;
    private final int maxUser;

    public SessionInfo(LectureStatus lectureStatus, Students students, int maxUser) {
        this.lectureStatus = lectureStatus;
        this.students = students;
        this.maxUser = maxUser;
    }

    public void register(Student student) {
        if (!this.lectureStatus.isRecruiting()) {
            throw new RuntimeException("수강신청은 모집중일때 가능합니다.");
        }
        students.add(student, maxUser);
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
}
