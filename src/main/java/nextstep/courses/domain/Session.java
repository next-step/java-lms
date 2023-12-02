package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private final Long courseId;
    private final SessionDuration sessionDuration;
    private final SessionEnrolment sessionEnrolment;
    private final CoverImages coverImages;

    public Session(Long id, Long courseId, SessionDuration sessionDuration, SessionEnrolment sessionEnrolment) {
        this(id, courseId, sessionDuration, sessionEnrolment, new CoverImages());
    }

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

    public void enrolment(NsUser student, Long userPayed) {
        if (isFree()) {
            sessionEnrolment.freeEnrolment(student);
            return;
        }

        sessionEnrolment.payEnrolment(student, userPayed);
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

    public boolean isMaxStudent() {
        return this.sessionEnrolment.isFullStudents();
    }

    public boolean isFree() {
        return this.sessionEnrolment.isFree();
    }
}
