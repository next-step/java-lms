package nextstep.courses.domain.registration;

import java.util.HashSet;
import java.util.Set;

public class SessionRegistration {
    private final SessionStatus sessionStatus;
    private final SessionRecruitmentStatus sessionRecruitmentStatus;
    private final Students students;

    public SessionRegistration(SessionStatus sessionStatus, SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserCount) {
        this(sessionStatus, sessionRecruitmentStatus, maxUserCount, new HashSet<>());
    }

    public SessionRegistration(SessionStatus sessionStatus, SessionRecruitmentStatus sessionRecruitmentStatus, int maxUserCount, Set<Student> students) {
        this(sessionStatus, sessionRecruitmentStatus, new Students(maxUserCount, students));
    }

    public SessionRegistration(SessionStatus sessionStatus, SessionRecruitmentStatus sessionRecruitmentStatus, Students students) {
        this.sessionStatus = migrationStatus(sessionStatus);
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
        this.students = students;
    }

    private SessionStatus migrationStatus(SessionStatus sessionStatus) {
        if (sessionStatus.isRecruiting()) {
            return SessionStatus.PROGRESSING;
        }

        return sessionStatus;
    }

    public void enroll(Student student, Set<Student> value) {
        validateSessionStatus(sessionStatus, sessionRecruitmentStatus);
        Students students = new Students(this.getMaxUserCount(),value);
        students.enroll(student);
    }

    private void validateSessionStatus(SessionStatus sessionStatus, SessionRecruitmentStatus recruitmentStatus) {
        if (sessionStatus.isNotProgressing() || recruitmentStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }
    }

    public Set<Student> getStudents() {
        return students.getUsers();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionRecruitmentStatus getSessionRecruitmentStatus() {
        return sessionRecruitmentStatus;
    }

    public int getMaxUserCount() {
        return students.getMaxUserCount();
    }
}
