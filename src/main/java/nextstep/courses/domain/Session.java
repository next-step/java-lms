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

    public void enrolment(NsUser student, Long userPayed) {
        sessionEnrolment.enrolment(student, userPayed);
    }

    public void approve(Student student, Long instructorId) {
        validate(instructorId);

        this.sessionEnrolment.approve(student);
    }

    public void updateInstructor(Long instructorId) {
        this.instructorId = instructorId;
    }

    public boolean isFullStudents() {
        return this.sessionEnrolment.isFullStudents();
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

    public int maxStudentCount() {
        return this.sessionEnrolment.maxStudent();
    }

    public boolean isFree() {
        return this.sessionEnrolment.isFree();
    }

    private void validate(Long instructorId) {
        if (!this.isInstructor(instructorId)) {
            throw new IllegalArgumentException("해당 강의의 강사만 가능합니다.");
        }
    }

    private boolean isInstructor(Long instructorId) {
        return this.instructorId.equals(instructorId);
    }
}
