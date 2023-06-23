package nextstep.courses.domain;

import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.SessionRecruitmentStatus;
import nextstep.courses.domain.registration.SessionStatus;
import nextstep.courses.domain.registration.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Set;

public class Session {

    private Long id;

    private SessionStatus sessionStatus;
    private final SessionRegistration sessionRegistration;
    private final SessionPeriod sessionPeriod;
    private final String sessionCoverImage;
    private final SessionCostType sessionCostType;

    public Session(Long id, SessionStatus sessionStatus, SessionRegistration sessionRegistration, SessionPeriod sessionPeriod, String sessionCoverImage, SessionCostType sessionCostType) {
        this.id = id;
        this.sessionStatus = sessionStatus;
        this.sessionRegistration = sessionRegistration;
        this.sessionPeriod = sessionPeriod;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionCostType = sessionCostType;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime startedAt() {
        return this.sessionPeriod.getStartedAt();
    }

    public LocalDateTime endedAt() {
        return this.sessionPeriod.getEndedAt();
    }

    public Set<Student> getUsers() {
        return sessionRegistration.getStudents();
    }

    public String getSessionCoverImage() {
        return sessionCoverImage;
    }

    public SessionCostType getSessionCostType() {
        return this.sessionCostType;
    }

    public SessionStatus getSessionStatus() {
        return this.sessionStatus;
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return sessionRegistration.getSessionRecruitmentStatus();
    }

    public int getMaxUserCount() {
        return sessionRegistration.getMaxUserCount();
    }

    public Student register(NsUser nsUser, Set<Student> students) {
        validateSessionStatus();
        Student student = new Student(nsUser.getId(), this.id);
        sessionRegistration.enroll(student, students);
        return student;
    }

    private void validateSessionStatus() {
        if (this.sessionStatus.isNotProgressing()) {
            throw new IllegalArgumentException("해당 강의는 진행중이 아닙니다.");
        }
    }

    public Session migrationStatus() {
        if (sessionRegistration.getSessionRecruitmentStatus().isRecruiting()) {
            return new Session(this.id, SessionStatus.PROGRESSING, this.sessionRegistration, this.sessionPeriod, this.sessionCoverImage, this.sessionCostType);
        }
        return this;
    }
}