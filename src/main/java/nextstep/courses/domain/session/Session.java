package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public abstract class Session {

    protected final long id;
    protected final long courseId;
    protected final SessionBody sessionBody;
    protected final SessionEnrollment sessionEnrollment;

    protected Session(long id, long courseId, SessionBody sessionBody, SessionEnrollment sessionEnrollment) {
        this.id = id;
        this.courseId = courseId;
        this.sessionBody = sessionBody;
        this.sessionEnrollment = sessionEnrollment;
    }

    public abstract void enroll(Student student, Payment payment);

    public void approve(Student student) {
        sessionEnrollment.approveStudent(student);
    }

    public void reject(Student student) {
        sessionEnrollment.rejectStudent(student);
    }

    public abstract long getFee();

    public abstract int getMaxEnrollments();

    public void validateSessionStatus() {
        if (isInValidSessionStatus()) {
            throw new IllegalStateException("진행중 또는 모집중인 상태에서만 신청 가능합니다.");
        }
    }

    private boolean isInValidSessionStatus() {
        return isNotInProgress() && isNotRecruiting();
    }

    private boolean isNotInProgress() {
        return sessionEnrollment.isNotInProgress();
    }

    private boolean isNotRecruiting() {
        return sessionEnrollment.isNotRecruiting();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return sessionBody.getTitle();
    }

    public SessionPeriod getPeriod() {
        return sessionBody.getPeriod();
    }

    public List<CoverImage> getCoverImages() {
        return sessionBody.getCoverImages();
    }

    public Set<Student> getEnrolledStudents() {
        return sessionEnrollment.getEnrolledStudents();
    }

    public LocalDateTime getStartDate() {
        return sessionBody.getPeriod().getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionBody.getPeriod().getEndDate();
    }

    public String getProgressStatus() {
        return sessionEnrollment.getProgressStatus().name();
    }

    public String getRecruitmentStatus() {
        return sessionEnrollment.getRecruitmentStatus().name();
    }

    public long getCourseId() {
        return courseId;
    }

}
