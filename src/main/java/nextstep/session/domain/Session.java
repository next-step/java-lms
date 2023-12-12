package nextstep.session.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Session extends BaseDomain implements Sessionable {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    private Long id;
    private Long creatorId;

    private SessionDate sessionDate;

    private SessionImage sessionImage;

    private SessionStatus sessionStatus;
    private SessionType sessionType;
    protected Enrollments enrollments = new Enrollments();

    public Session(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType) {
        super();
        this.creatorId = creatorId;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionImage = sessionImage;
        this.sessionStatus = DEFAULT_SESSION_STATUS;
        this.sessionType = sessionType;
    }

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionType sessionType) {
        super(createdAt, updatedAt);
        this.id = id;
        this.creatorId = creatorId;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }

    @Override
    public void enroll(NsUser user) {
        validateStatus();
        validateCommonEnroll(user);
        enrollments.add(user);
    }

    private void validateStatus() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }
    }

    protected void validateCommonEnroll(NsUser nsUser) {
    }

    @Override
    public int enrolledNumber() {
        return enrollments.enrolledNumber();
    }

    @Override
    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }

    public Long getId() {
        return id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public SessionImage getSessionImage() {
        return sessionImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionType getSessionType() {
        return sessionType;
    }
}
