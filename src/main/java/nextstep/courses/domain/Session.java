package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {

    protected long id;
    protected long amount = 0;
    protected long maximumNumberOfStudent;
    protected SessionCoverImage coverImage;
    protected List<NsUser> students = new ArrayList<>();
    protected LocalDateTime startedAt;
    protected LocalDateTime endedAt;
    protected SessionStatus status = SessionStatus.PREPARING;
    protected ProgressStatus progressStatus = ProgressStatus.PREPARING;
    protected RecruitmentStatus recruitmentStatus = RecruitmentStatus.NOT_RECRUITING;
    protected SessionType type;

    protected Session(long id, long maximumNumberOfStudent, LocalDateTime startedAt, LocalDateTime endedAt, SessionCoverImage coverImage, SessionType type) {
        this(id, 0, maximumNumberOfStudent, startedAt, endedAt, coverImage, type);
    }

    protected Session(long id, long amount, long maximumNumberOfStudent, LocalDateTime startedAt, LocalDateTime endedAt, SessionCoverImage coverImage, SessionType type) {
        this.id = id;
        this.amount = amount;
        this.maximumNumberOfStudent = maximumNumberOfStudent;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.coverImage = coverImage;
        this.type = type;
    }

    public void changeStatus(SessionStatus status) {
        this.status = status;
    }

    public void changeProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public void changeRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public boolean isStatus(SessionStatus status) {
        return this.status.isSame(status);
    }

    public boolean isProgressStatus(ProgressStatus progressStatus) {
        return this.progressStatus.isSame(progressStatus);
    }

    public boolean isRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
        return this.recruitmentStatus.isSame(recruitmentStatus);
    }

    public List<NsUser> totalStudents() {
        return new ArrayList<>(this.students);
    }

    public long getId() {
        return this.id;
    }

    public void enroll(NsUser user, Payment payment) {
        validateRecruiting();
        this.students.add(user);
    }

    protected void validateRecruiting() {
        if (this.recruitmentStatus.isSame(RecruitmentStatus.NOT_RECRUITING)) {
            throw new IllegalStateException("현재 모집 중인 강의가 아닙니다.");
        }
    }

    public long getMaximumNumberOfStudent() {
        return maximumNumberOfStudent;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public long getAmount() {
        return amount;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public SessionType getType() {
        return type;
    }
}
