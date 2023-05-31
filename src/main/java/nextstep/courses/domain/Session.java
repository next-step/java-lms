package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Session {
    private static final String SESSION_STATUS_NOT_IN_PROGRESS = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";
    private static final String CANNOT_ENROLL_SESSION_STATUS = "강의 수강신청은 강의 상태가 진행중이며 모집중 때만 가능합니다.";
    private static final String NOT_FOUND_SESSION_PAYMENT = "지불상태를 찾을 수 없습니다. (유료/무료)를 선택해주세요.";
    private static final String ALREADY_ENROLLED_USER = "이미 등록한 사용자 입니다.";


    private Long id;
    private final SessionPeriod sessionPeriod;
    private final SessionCoverImage sessionCoverImage;
    private SessionStatus sessionStatus;
    private SessionEnrollmentStatus sessionEnrollmentStatus;
    private final SessionPayment sessionPayment;
    private SessionUsers sessionUsers;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public Session(SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionEnrollmentStatus sessionEnrollmentStatus, SessionPayment sessionPayment, SessionUsers sessionUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, sessionPeriod, sessionCoverImage, sessionStatus, sessionEnrollmentStatus, sessionPayment, sessionUsers, createdAt, updatedAt);
    }

    public Session(Long id, SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionEnrollmentStatus sessionEnrollmentStatus, SessionPayment sessionPayment, SessionUsers sessionUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (sessionPayment == null) {
            throw new IllegalArgumentException(NOT_FOUND_SESSION_PAYMENT);
        }
        if (sessionEnrollmentStatus == null) {
            sessionEnrollmentStatus = SessionEnrollmentStatus.NO_INFO;
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
        boolean isEnrolledUser = sessionUsers.getSessionUsers().stream()
                .map(sessionUser -> sessionUser.isIncludeNsUserId(nsUser))
                .reduce(Boolean::logicalAnd)
                .orElse(false);
        if (!sessionStatus.canEnrollment()) {
            throw new IllegalArgumentException(SESSION_STATUS_NOT_IN_PROGRESS);
        }
        if (!sessionEnrollmentStatus.canEnrollment()) {
            throw new IllegalArgumentException(CANNOT_ENROLL_SESSION_STATUS);
        }
        if (isEnrolledUser) {
            throw new IllegalArgumentException(ALREADY_ENROLLED_USER);
        }
        LocalDateTime now = LocalDateTime.now();
        ApprovalStatus approvalStatus = ApprovalStatus.REQUEST;
        sessionUsers.addEnroll(new SessionUser(this, approvalStatus, nsUser, now, now));
    }


    public void startEnroll() {
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
