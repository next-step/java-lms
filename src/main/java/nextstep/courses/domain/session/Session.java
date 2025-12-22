package nextstep.courses.domain.session;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentCandidate;
import nextstep.courses.domain.image.SessionImage;
import nextstep.courses.domain.image.SessionImages;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;
    private final SessionType sessionType;

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image) {
        this(new SessionInfo(1, startDate, endDate, image),
                ProgressStatus.PREPARING,
                RecruitmentStatus.NOT_RECRUITING,
                new FreeSessionType());
    }

    public Session(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image,
                   ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, SessionType type) {
        this(null, new SessionInfo(cohort, startDate, endDate, image), progressStatus, recruitmentStatus, type);
    }

    public Session(Long id, int cohort, LocalDate startDate, LocalDate endDate, SessionImage image,
                   ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, SessionType type) {
        this(id, new SessionInfo(cohort, startDate, endDate, image), progressStatus, recruitmentStatus, type);
    }

    public Session(SessionInfo info, ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, SessionType type) {
        this(null, info, progressStatus, recruitmentStatus, type);
    }

    public Session(Long id, SessionInfo sessionInfo, ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, SessionType sessionType) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.sessionType = sessionType;
    }


    public Enrollment createEnrollment(List<EnrollmentCandidate> currentStudents) {
        return new Enrollment(id, recruitmentStatus, sessionType, currentStudents);
    }

    public Long getId() {
        return id;
    }

    public int getCohort() {
        return sessionInfo.getCohort();
    }

    public SessionImage getImage() {
        return sessionInfo.getImage();
    }

    public SessionImages getImages() {
        return sessionInfo.getImages();
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
