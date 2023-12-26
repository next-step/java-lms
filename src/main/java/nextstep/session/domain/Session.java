package nextstep.session.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public abstract class Session extends BaseDomain implements Sessionable {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;
    private static final SessionRecruitStatus DEFAULT_RECRUIT_STATUS = SessionRecruitStatus.CLOSED;

    private Long id;
    private Long creatorId;

    private SessionDate sessionDate;

    private SessionImage sessionImage;

    private SessionStatus sessionStatus;
    private SessionRecruitStatus sessionRecruitStatus;
    private SessionType sessionType;
    protected Enrollments enrollments = new Enrollments();
    protected Admissions admissions = new Admissions();

    public Session(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType) {
        this(null, null, null, creatorId, startDate, endDate, sessionImage, DEFAULT_SESSION_STATUS, DEFAULT_RECRUIT_STATUS, sessionType, null, null);
    }

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, SessionType sessionType, List<Enrollment> enrollments, List<Admission> admissions) {
        super(createdAt, updatedAt);
        this.id = id;
        this.creatorId = creatorId;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.sessionType = sessionType;
        this.enrollments = new Enrollments(enrollments);
        this.admissions = new Admissions(admissions);
    }

    @Override
    public Enrollment enroll(NsUser user) {
        validateStatus();
        validateCommonEnroll(user);
        return enrollments.add(user, this);
    }

    private void validateStatus() {
        if (sessionStatus == SessionStatus.END) {
            throw new IllegalStateException("종료된 강의는 신청 불가능합니다.");
        }
        if (sessionRecruitStatus != SessionRecruitStatus.OPEN) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }
    }

    abstract protected void validateCommonEnroll(NsUser nsUser);

    @Override
    public int enrolledNumber() {
        return enrollments.enrolledNumber();
    }

    public Enrollment admiss(NsUser loginUser, NsUser student) {
        if (!creatorId.equals(loginUser.getId())) {
            throw new IllegalArgumentException("강사만 승인할 수 있습니다.");
        }
        if (admissions.isAdmiss(student, this)) {
            throw new IllegalArgumentException("선발되지 않은 학생입니다.");
        }
        return enrollments.admiss(student, this);
    }

    public Enrollment cancel(NsUser loginUser, NsUser student) {
        if (!creatorId.equals(loginUser.getId())) {
            throw new IllegalArgumentException("강사만 승인할 수 있습니다.");
        }
        return enrollments.cancel(student, this);
    }

    @Override
    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }

    public void changeRecruit(SessionRecruitStatus recruitStatus) {
        if (recruitStatus.equals(SessionRecruitStatus.OPEN)) {
            SessionRecruitStatus.enableOpen(sessionStatus);
        }
        this.sessionRecruitStatus = recruitStatus;
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

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRecruitStatus;
    }

    public SessionType getSessionType() {
        return sessionType;
    }
}
