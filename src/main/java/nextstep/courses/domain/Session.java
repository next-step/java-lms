package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionState;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private SessionDate sessionDate;
    private String coverImagePath;
    private boolean isFree;
    private SessionStatus status;
    private SessionState state;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(String startDate, String endDate, int maxCapacity, Long courseId) {
        this(1L, startDate, endDate, " ", false,
                maxCapacity, courseId);
    }

    public Session(Long id, String startDate, String endDate, String coverImagePath, boolean isFree,
                   int maxCapacity, Long courseId) {
        this(id, new SessionDate(startDate, endDate), coverImagePath, isFree,
                SessionState.PREPARING, maxCapacity, courseId, LocalDateTime.now(), null);
    }

//    public Session(Long id, String startDate, String endDate, String coverImagePath, boolean isFree, int state,
//                   int maxCapacity, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this(id, new SessionDate(startDate, endDate), coverImagePath, isFree, SessionState.of(state),
//                maxCapacity, courseId, createdAt, updatedAt);
//    }

    public Session(Long id, SessionDate sessionDate, String coverImagePath, boolean isFree, SessionState state,
                   int maxCapacity, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.isFree = isFree;
        this.status = new SessionStatus(maxCapacity);
        this.state = state;
        this.coverImagePath = coverImagePath;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void enroll(NsUser student) {
        if (!state.equals(SessionState.PROCEEDING)) {
            throw new SessionNotOpenException("강의가 모집중이 아니어서 신청이 불가합니다.");
        }

        status.enroll(student, this.id);
    }


    public void setSessionState(SessionState requestState) {
        if (sessionDate.isExpired()) {
            throw new SessionExpiredException("강의종료일이 경과하여 상태 변경이 불가합니다.");
        }

        state = requestState;
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

    public boolean equalsState(SessionState state) {
        return this.state.equals(state);
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

    public int getState() {
        return state.getInt();
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
