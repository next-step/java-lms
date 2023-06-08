package nextstep.courses.domain;

import nextstep.courses.domain.registration.SessionRecruitmentStatus;
import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.SessionStatus;
import nextstep.courses.domain.registration.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class Session {
    private Long id;
    private final SessionRegistration sessionRegistration;
    private final SessionPeriod sessionPeriod;
    private final String sessionCoverImage;
    private final SessionCostType sessionCostType;

    public Session(Long id, SessionRegistration sessionRegistration, SessionPeriod sessionPeriod, String sessionCoverImage, SessionCostType sessionCostType) {
        this.id = id;
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
        return sessionRegistration.getSessionStatus();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return sessionRegistration.getSessionRecruitmentStatus();
    }

    public int getMaxUserCount() {
        return sessionRegistration.getMaxUserCount();
    }

    public Student register(NsUser nsUser, Set<Student> students) {
        Student student = new Student(nsUser.getId(), this.id);
        sessionRegistration.enroll(student, students);
        return student;
    }

    public Student approve(Student student, CourseUser courseUser) {
        if (Optional.ofNullable(courseUser).isEmpty()) {
            throw new IllegalArgumentException("과정에 신청하지 않은 사용자 입니다.");
        }

        if (courseUser.isNotSelected()) {
            throw new IllegalArgumentException("선발되지 않은 사용자 입니다.");
        }

        student.approve();
        return student;
    }
}
