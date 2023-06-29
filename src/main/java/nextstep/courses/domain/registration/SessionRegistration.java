package nextstep.courses.domain.registration;

import java.util.HashSet;
import java.util.Set;

public class SessionRegistration {
    private final SessionRecruitmentStatus sessionRecruitmentStatus;
    private final Students students;

    public SessionRegistration(SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserCount) {
        this(sessionRecruitmentStatus, maxUserCount, new HashSet<>());
    }

    public SessionRegistration(SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserCount, Set<Student> students) {
        this(sessionRecruitmentStatus, new Students(maxUserCount, students));
    }

    public SessionRegistration(SessionRecruitmentStatus sessionRecruitmentStatus, Students students) {
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
        this.students = students;
    }

    public void enroll(Student student, Set<Student> value) {
        validateSessionStatus(sessionRecruitmentStatus);
        Students students = new Students(this.getMaxUserCount(),value);
        students.enroll(student);
    }

    private void validateSessionStatus(SessionRecruitmentStatus recruitmentStatus) {
        if (recruitmentStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }
    }

    public Set<Student> getStudents() {
        return students.getUsers();
    }

    public SessionRecruitmentStatus getSessionRecruitmentStatus() {
        return sessionRecruitmentStatus;
    }

    public int getMaxUserCount() {
        return students.getMaxUserCount();
    }
}
