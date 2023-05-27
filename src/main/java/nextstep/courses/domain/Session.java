package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class Session {
    private static final String CANNOT_ENROLL_SESSION1 = "강의 수강신청은 강의 상태가 진행중일 때만 가능합니다.";
    private static final String CANNOT_ENROLL_SESSION2 = "강의 수강신청은 강의 상태가 진행중이며 모집중 때만 가능합니다.";
    private static final String NOT_FOUND_SESSION_PAYMENT = "우아한테크코스(무료), 우아한테크캠프 Pro(유료)와 같이 선발된 인원만 수강 가능합니다.";

    private Long id;
    private final SessionPeriod sessionPeriod;
    private final SessionCoverImage sessionCoverImage;
    private SessionStatus sessionStatus;
    private SessionEnrollmentStatus sessionEnrollmentStatus;
    private final SessionPayment sessionPayment;
    private final SessionUsers sessionUsers;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public Session(SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionEnrollmentStatus sessionEnrollmentStatus, SessionPayment sessionPayment, SessionUsers sessionUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, sessionPeriod, sessionCoverImage, sessionStatus, sessionEnrollmentStatus, sessionPayment, sessionUsers, createdAt, updatedAt);
    }

    public Session(Long id, SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionEnrollmentStatus sessionEnrollmentStatus, SessionPayment sessionPayment, SessionUsers sessionUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (sessionPayment == null) {
            throw new IllegalArgumentException(NOT_FOUND_SESSION_PAYMENT);
        }
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionStatus = sessionStatus;
        this.sessionEnrollmentStatus = sessionEnrollmentStatus;
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
            throw new IllegalArgumentException(CANNOT_ENROLL_SESSION1);
        }
        if (!sessionEnrollmentStatus.canEnrollment()) {
            throw new IllegalArgumentException(CANNOT_ENROLL_SESSION2);
        }
        LocalDateTime now = LocalDateTime.now();
        sessionUsers.addEnroll(new SessionUser(this, ApprovalStatus.REQUEST, nsUser, now, now));
    }

    public void enroll() {
        this.sessionEnrollmentStatus = SessionEnrollmentStatus.ENROLLMENT;
    }

    public void nonEnroll() {
        this.sessionEnrollmentStatus = SessionEnrollmentStatus.NON_ENROLLMENT;
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

    public SessionEnrollmentStatus getSessionEnrollmentStatus() {
        return sessionEnrollmentStatus;
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
                ", sessionEnrollmentStatus=" + sessionEnrollmentStatus +
                ", sessionPayment=" + sessionPayment +
                ", sessionUsers=" + sessionUsers +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
