package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final SessionStatus status;
    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;
    private final SessionType sessionType;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(new SessionPeriod(startDate, endDate), coverImage, SessionStatus.from(status), new FreeSessionType());
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus sessionStatus) {
        this(cohort, startDate, endDate, image, sessionStatus, new FreeSessionType());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image, String status, int maximumCapacity, long fee) {
        this(new SessionPeriod(startDate, endDate), image, SessionStatus.from(status), new PaidSessionType(maximumCapacity, fee));
    }

    public Session(SessionPeriod sessionPeriod, SessionImage image, SessionStatus from, SessionType sessionType) {
        this(null, 1, sessionPeriod, image, from, sessionType);
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status, SessionType type) {
        this(null, cohort, new SessionPeriod(startDate, endDate), image, status, type);
    }

    public Session(long id, int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status, SessionType type) {
        this(id, cohort, new SessionPeriod(startDate, endDate), image, status, type);
    }

    public Session(Long id, int cohort, SessionPeriod period, SessionImage coverImage, SessionStatus status, SessionType sessionType) {
        this(id, new SessionInfo(cohort, period, coverImage), status, sessionType);
    }

    public Session(Long id, SessionInfo sessionInfo, SessionStatus status, SessionType sessionType) {
        this(id, sessionInfo, status, null, null, sessionType);
    }


    public Session(Long id, int cohort, LocalDate startDate, LocalDate endDate, SessionImage image, ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, SessionType sessionType) {
        this(id, new SessionInfo(cohort, startDate, endDate, image), null, progressStatus, recruitmentStatus, sessionType);
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image) {
        this(cohort, startDate, endDate, image, SessionStatus.PREPARING);
    }

    public Session(Long id, SessionInfo sessionInfo, SessionStatus status, ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, SessionType sessionType) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.status = status;
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.sessionType = sessionType;
    }


    public Enrollment createEnrollment(List<EnrolledStudent> currentStudents) {
        if (recruitmentStatus != null) {
            return new Enrollment(id, recruitmentStatus, sessionType, currentStudents);
        }
        return new Enrollment(id, status, sessionType, currentStudents);
    }

    public Long getId() {
        return id;
    }

    public int getCohort() {
        return sessionInfo.getCohort();
    }

    public SessionImage getImage() {
        return sessionInfo.getCoverImage();
    }

    public SessionStatus getStatus() {
        return status;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public LocalDate getStartDate() {
        return sessionInfo.getStartDate();
    }

    public LocalDate getEndDate() {
        return sessionInfo.getEndDate();
    }

}
