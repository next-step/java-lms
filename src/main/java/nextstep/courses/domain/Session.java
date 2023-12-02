package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private final Long courseId;
    private Long instructorId;
    private final SessionDuration sessionDuration;
    private final SessionEnrolment sessionEnrolment;
    private final CoverImages coverImages;

    public Session(Long courseId, SessionDuration sessionDuration, SessionEnrolment sessionEnrolment, CoverImages coverImages) {
        this.courseId = courseId;
        this.sessionDuration = sessionDuration;
        this.sessionEnrolment = sessionEnrolment;
        this.coverImages = coverImages;
    }

    public Session(Long id, Long courseId, SessionDuration sessionDuration, SessionEnrolment sessionEnrolment, CoverImages coverImages) {
        this.id = id;
        this.courseId = courseId;
        this.sessionDuration = sessionDuration;
        this.sessionEnrolment = sessionEnrolment;
        this.coverImages = coverImages;
    }

    public Apply enrolment(NsUser student, Long userPayed) {
        sessionEnrolment.enrolment(userPayed);
        return Apply.defaultApply(this, student);
    }

    public void addStudent(NsUser student) {
        this.sessionEnrolment.addStudent(student);
    }

    public boolean isinstructor(Long instructorId) {
        return this.instructorId.equals(instructorId);
    }

    public void updateInstructor(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long id() {
        return this.id;
    }

    public Long courseId() {
        return this.courseId;
    }

    public LocalDateTime startDate() {
        return this.sessionDuration.startDate();
    }

    public LocalDateTime endDate() {
        return this.sessionDuration.endDate();
    }

    public String sessionStatus() {
        return this.sessionEnrolment.sessionStatusType();
    }

    public String recruitmentStatus() {
        return this.sessionEnrolment.recruitmentStatusType();
    }

    public Long sessionAmount() {
        return this.sessionEnrolment.amount();
    }

    public int totalStudentCount() {
        return this.sessionEnrolment.totalStudent();
    }

    public boolean isFree() {
        return this.sessionEnrolment.isFree();
    }
}
