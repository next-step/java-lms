package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private static final String CANNOT_ENROLL_SESSION = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";

    private Long id;
    private final SessionPeriod sessionPeriod;
    private final SessionCoverImage sessionCoverImage;
    private SessionStatus sessionStatus;
    private final SessionPayment sessionPayment;
    private final SessionUsers sessionUsers;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Session(SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionPayment sessionPayment, SessionUsers sessionUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, sessionPeriod, sessionCoverImage, sessionStatus, sessionPayment, sessionUsers, createdAt, updatedAt);
    }

    public Session(Long id, SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionPayment sessionPayment, SessionUsers sessionUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionStatus = sessionStatus;
        this.sessionPayment = sessionPayment;
        this.sessionUsers = sessionUsers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserCount() {
        return sessionUsers.getUserCount();
    }

    public void changeSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public void enrollSession(NsUser nsUser) {
        if (!sessionStatus.canEnrollment()) {
            throw new IllegalArgumentException(CANNOT_ENROLL_SESSION);
        }
        sessionUsers.addEnroll(new SessionUser(this, nsUser, LocalDateTime.now(), LocalDateTime.now()));
    }

    public LocalDateTime getCreatedAt() {
        return LocalDateTime.now();
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public SessionCoverImage getSessionCoverImage() {
        return sessionCoverImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionPayment getSessionPayment() {
        return sessionPayment;
    }

    public SessionUsers getSessionUsers() {
        return sessionUsers;
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionPeriod=" + sessionPeriod +
                ", sessionCoverImage=" + sessionCoverImage +
                ", sessionStatus=" + sessionStatus +
                ", sessionPayment=" + sessionPayment +
                ", sessionUsers=" + sessionUsers +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
