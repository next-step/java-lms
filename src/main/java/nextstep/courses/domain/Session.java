package nextstep.courses.domain;

import nextstep.courses.domain.enums.ProgressState;
import nextstep.courses.domain.enums.RecruitmentState;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private SessionDate sessionDate;
    private String coverImagePath;
    private boolean isFree;
    private ProgressState progressState;
    private RecruitmentState recruitmentState;
    private int maxCapacity;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SessionStatus status;

    public Session(String startDate, String endDate, int maxCapacity, Long courseId) {
        this(1L, startDate, endDate, " ", false,
                maxCapacity, courseId);
    }

    public Session(Long id, String startDate, String endDate, String coverImagePath, boolean isFree,
                   int maxCapacity, Long courseId) {
        this(id, new SessionDate(startDate, endDate), coverImagePath, isFree,
                ProgressState.PREPARING, RecruitmentState.RECRUITING, maxCapacity, courseId, LocalDateTime.now(), null);
    }

    public Session(Long id, SessionDate sessionDate, String coverImagePath, boolean isFree, ProgressState progressState,
                   RecruitmentState recruitmentState, int maxCapacity, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.coverImagePath = coverImagePath;
        this.isFree = isFree;
        this.progressState = progressState;
        this.recruitmentState = recruitmentState;
        this.maxCapacity = maxCapacity;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.status = new SessionStatus(this.maxCapacity, this.progressState, this.recruitmentState);
    }

    public void enroll(NsUser student) {
        status.enroll(student, this.id);
    }

    public void setSessionState(ProgressState requestState) {
        if (sessionDate.isExpired()) {
            throw new SessionExpiredException("강의종료일이 경과하여 상태 변경이 불가합니다.");
        }

        progressState = requestState;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", SessionDate='" + sessionDate +
                ", coverImagePath=" + coverImagePath +
                ", isFree=" + isFree +
                ", coverImagePath=" + coverImagePath +
                ", courseId=" + courseId +
                ", createdAt=" + createdAt +
                '}';
    }

    public boolean equalsState(ProgressState state) {
        return this.progressState.equals(state);
    }

    public int getSignedUpStatus() {
        return status.getEnrollmentSize();
    }

    public String getStartDate() {
        return sessionDate.getStartDate();
    }

    public String getEndDate() {
        return sessionDate.getEndDate();
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public boolean isFree() {
        return isFree;
    }

    public int getMaxCapacity() {
        return status.getMaxCapacity();
    }

    public int getProgressState() {
        return progressState.getInt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getCourseId() {
        return courseId;
    }
}
