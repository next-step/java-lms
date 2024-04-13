package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Session {

    protected long id;
    protected long courseId;
    protected List<Long> coverImageIds;
    protected List<NsUser> students = new ArrayList<>();
    protected List<Enrollment> enrollments = new ArrayList<>();
    protected long amount = 0;
    protected long maximumNumberOfStudent;
    protected LocalDateTime startedAt;
    protected LocalDateTime endedAt;
    protected SessionStatus status = SessionStatus.PREPARING;
    protected ProgressStatus progressStatus = ProgressStatus.PREPARING;
    protected RecruitmentStatus recruitmentStatus = RecruitmentStatus.NOT_RECRUITING;
    protected SessionType type;

    protected Session(long id,
                      long courseId,
                      List<Long> coverImageIds,
                      long maximumNumberOfStudent,
                      LocalDateTime startedAt,
                      LocalDateTime endedAt,
                      SessionType type) {
        this(id, courseId, coverImageIds, 0, maximumNumberOfStudent, startedAt, endedAt, type);
    }

    protected Session(long id,
                      long courseId,
                      List<Long> coverImageIds,
                      long amount,
                      long maximumNumberOfStudent,
                      LocalDateTime startedAt,
                      LocalDateTime endedAt,
                      SessionType type) {
        this.id = id;
        this.courseId = courseId;
        this.coverImageIds = coverImageIds;
        this.amount = amount;
        this.maximumNumberOfStudent = maximumNumberOfStudent;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.type = type;
    }

    public void changeProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public void changeRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
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

    public void enroll(NsUser user, Payment payment) {
        validateRecruiting();
        this.students.add(user);
        this.enrollments.add(new Enrollment(user.getId(), this.id));
    }

    protected void validateRecruiting() {
        if (this.recruitmentStatus.isSame(RecruitmentStatus.NOT_RECRUITING)) {
            throw new IllegalStateException("현재 모집 중인 강의가 아닙니다.");
        }
    }

    public void approveEnrollment(NsUser user) {
        this.enrollments.stream()
                .filter(enrollment -> Objects.equals(enrollment.getUserId(), user.getId()))
                .findFirst()
                .ifPresent(enrollment -> enrollment.changeEnrollmentStatus(EnrollmentStatus.APPROVED));
    }

    public long getId() {
        return this.id;
    }

    public long getCourseId() {
        return courseId;
    }

    public List<Long> getCoverImageIds() {
        return coverImageIds;
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

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public long getAmount() {
        return amount;
    }

    public SessionType getType() {
        return type;
    }
}
