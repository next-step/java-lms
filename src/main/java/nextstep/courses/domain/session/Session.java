package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
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

    public abstract void enroll(NsUser nsUser, Payment payment);

    public abstract long getFee();

    public abstract int getMaxEnrollments();

    public void validateSessionStatus() {
        if (isNotOpen()) {
            throw new IllegalStateException("모집중인 상태에서만 신청 가능합니다.");
        }
    }

    public boolean isNotOpen() {
        return sessionEnrollment.isNotOpen();
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

    public CoverImage getCoverImage() {
        return sessionBody.getCoverImage();
    }

    public Set<NsUser> getEnrolledUsers() {
        return sessionEnrollment.getEnrolledUsers();
    }

    public LocalDateTime getStartDate() {
        return sessionBody.getPeriod().getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionBody.getPeriod().getEndDate();
    }

    public String getSessionStatus() {
        return sessionEnrollment.getSessionStatus().name();
    }

    public long getCourseId() {
        return courseId;
    }

    public String getFileName() {
        return sessionBody.getCoverImage().getFileName();
    }

    public int getImageSize() {
        return sessionBody.getCoverImage().getImageSize();
    }

    public String getImageExtension() {
        return sessionBody.getCoverImage().getExtension().name();
    }

    public int getWidth() {
        return sessionBody.getCoverImage().getWidth();
    }

    public int getHeight() {
        return sessionBody.getCoverImage().getHeight();
    }

}
