package nextstep.courses.domain.session;

import nextstep.courses.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;
import static nextstep.courses.ExceptionMessage.UNAUTHORIZED;
import static nextstep.courses.domain.session.EnrollmentStatus.*;

public class Enrollment {
    private Long id;
    private Session session;
    private NsUser enrolledUser;
    private EnrollmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(Session session, NsUser enrolledUser) {
        this(null, session, enrolledUser, WAITING, LocalDateTime.now(), null);
    }

    public Enrollment(Long id, Session session, NsUser enrolledUser, EnrollmentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.enrolledUser = enrolledUser;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isApproved() {
        return status.isApproved();
    }

    public void approve(NsUser user) {
        updateStatus(user, APPROVED);
    }

    public void reject(NsUser user) {
        updateStatus(user, REJECTED);
    }

    public void wait(NsUser user) {
        updateStatus(user, WAITING);
    }

    private void updateStatus(NsUser user, EnrollmentStatus status) {
        validateSessionCreator(user);
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    private void validateSessionCreator(NsUser user) {
        if (!session.isSessionCreator(user)) {
            throw new UnAuthorizedException(UNAUTHORIZED.message());
        }
    }

    public void updateAsSavedEnrollment(Long id) {
        if (nonNull(this.id)) {
            return;
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public Long getIdOfSession() {
        return session.getId();
    }

    public NsUser getEnrolledUser() {
        return enrolledUser;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
