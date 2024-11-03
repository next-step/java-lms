package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Set;

public abstract class Session {

    protected Long id;
    protected SessionBody sessionBody;
    protected SessionEnrollment sessionEnrollment;

    protected Session(Long id, SessionBody sessionBody, SessionEnrollment sessionEnrollment) {
        this.id = id;
        this.sessionBody = sessionBody;
        this.sessionEnrollment = sessionEnrollment;
    }

    abstract public void enroll(NsUser nsUser, Payment payment);

    public void validateSessionStatus() {
        if (isNotOpen()) {
            throw new IllegalStateException("모집중인 상태에서만 신청 가능합니다.");
        }
    }

    private boolean isNotOpen() {
        return sessionEnrollment.isNotOpen();
    }

    public void validateDuplicateEnrollment(NsUser nsUser) {
        if (isDuplicateEnrolledUser(nsUser)) {
            throw new IllegalStateException("중복된 수강신청입니다.");
        }
    }

    private boolean isDuplicateEnrolledUser(NsUser nsUser) {
        return sessionEnrollment.contains(nsUser);
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
}
