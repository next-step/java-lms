package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Session {

    protected Long id;
    protected SessionBody sessionBody;
    protected Set<NsUser> enrolledUsers;

    protected Session(Long id, SessionBody sessionBody) {
        this.id = id;
        this.sessionBody = sessionBody;
        this.enrolledUsers = new HashSet<>();
    }

    abstract public void enroll(NsUser nsUser, Payment payment);

    public void validateSessionStatus() {
        if (isEnrollmentNotOpen()) {
            throw new IllegalStateException("모집중인 상태에서만 신청 가능합니다.");
        }
    }

    public void validateDuplicateEnrollment(NsUser nsUser) {
        if (isDuplicateEnrolledUser(nsUser)) {
            throw new IllegalStateException("중복된 수강신청입니다.");
        }
    }

    public void openEnrollment() {
        sessionBody.openSession();
    }

    private boolean isDuplicateEnrolledUser(NsUser nsUser) {
        return enrolledUsers.contains(nsUser);
    }

    private boolean isEnrollmentNotOpen() {
        return sessionBody.isNotOpen();
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
        return Collections.unmodifiableSet(enrolledUsers);
    }
}
