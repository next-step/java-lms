package nextstep.session.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.session.domain.policy.admiss.AdmissPolicy;
import nextstep.session.domain.policy.admiss.AdmissStudentPolicy;
import nextstep.session.domain.policy.admiss.AdmissTeacherPolicy;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public abstract class Session extends BaseDomain {
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

    private List<AdmissPolicy<Session>> admissPolicies = List.of(new AdmissStudentPolicy(), new AdmissTeacherPolicy());

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

    public int enrolledNumber() {
        return enrollments.enrolledNumber();
    }

    public abstract Enrollment enroll(NsUser user);

    public Enrollment admiss(NsUser loginUser, NsUser student) {
        admissPolicies.forEach(policy -> policy.validate(this, loginUser, student));
        return enrollments.admiss(student, this);
    }

    public Enrollment cancel(NsUser loginUser, NsUser student) {
        new AdmissTeacherPolicy().validate(this, loginUser, student);
        return enrollments.cancel(student, this);
    }

    public boolean isTeacher(NsUser user) {
        return creatorId.equals(user.getId());
    }

    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }

    public void changeRecruit(SessionRecruitStatus changeStatus) {
        if (changeStatus.isOpen()) {
            SessionRecruitStatus.enableOpen(sessionStatus);
        }
        this.sessionRecruitStatus = changeStatus;
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

    public Admissions getAdmissions() {
        return admissions;
    }
}
