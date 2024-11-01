package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.*;

public abstract class Session {

    protected Long id;
    protected String title;
    protected SessionPeriod period;
    protected CoverImage coverImage;
    protected SessionStatus sessionStatus;
    protected Set<NsUser> enrolledUsers;

    protected Session(Long id, String title, SessionPeriod period, CoverImage coverImage) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
        this.sessionStatus = SessionStatus.PREPARE;
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
        sessionStatus = SessionStatus.OPEN;
    }

    private boolean isDuplicateEnrolledUser(NsUser nsUser) {
        return enrolledUsers.contains(nsUser);
    }

    private boolean isEnrollmentNotOpen() {
        return sessionStatus.isNotOpen();
    }

    public String getTitle() {
        return title;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Set<NsUser> getEnrolledUsers() {
        return Collections.unmodifiableSet(enrolledUsers);
    }
}
