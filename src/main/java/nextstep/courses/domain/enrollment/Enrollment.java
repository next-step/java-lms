package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Enrollment {

    private Long id;

    private Session session;

    private NsUser user;

    private EnrollmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Enrollment(Session session, NsUser user, EnrollmentStatus status) {
        this(0L, session, user, status, LocalDateTime.now(), null);
    }

    public Enrollment(Long id, Session session, NsUser user, EnrollmentStatus status,
                      LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.user = user;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void approve() {
        if (user.isSameSessionType(session.getType())) {
            status = EnrollmentStatus.APPROVED;
            session.enroll();
            return;
        }

        status = EnrollmentStatus.CANCELED;
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public NsUser getUser() {
        return user;
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
