package nextstep.session.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Session extends BaseDomain implements Sessionable {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    private Long creatorId;

    private SessionDate sessionDate;

    private SessionImage sessionImage;

    private SessionStatus sessionStatus;
    private SessionType sessionType;
    protected Enrollment enrollment = new Enrollment();

    public Session(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType) {
        this(0L, LocalDateTime.now(), null, creatorId, startDate, endDate, sessionImage, DEFAULT_SESSION_STATUS, sessionType);
    }

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionType sessionType) {
        super(id, createdAt, updatedAt);
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
        enrollment.add(user);
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
        return enrollment.enrolledNumber();
    }

    @Override
    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
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
