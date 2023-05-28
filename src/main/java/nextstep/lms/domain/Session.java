package nextstep.lms.domain;

import java.time.LocalDate;

public class Session {

    private Long id;
    private SessionDate sessionDate;
    private Long imageId;
    private SessionState sessionState = SessionState.READY;
    private SessionType sessionType;
    private StudentCapacity studentCapacity;

    public Session(LocalDate startDate, LocalDate endDate) {
        this(null, startDate, endDate,
                SessionState.READY, SessionType.FREE, 0,
                1, null);
    }

    public Session(LocalDate startDate, LocalDate endDate, Long imageId,
                   SessionType sessionType, int studentCapacity) {
        this(null, startDate, endDate,
                SessionState.READY, sessionType, 0,
                studentCapacity, imageId);
    }

    public Session(Long id, LocalDate startDate, LocalDate endDate,
                   SessionState sessionState, SessionType sessionType, int registeredStudent,
                   int studentCapacity, Long imageId) {
        validateDate(startDate, endDate);

        this.id = id;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionState = sessionState;
        this.sessionType = sessionType;
        this.studentCapacity = new StudentCapacity(studentCapacity, registeredStudent);
        this.imageId = imageId;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
        }
    }

    public static Session createSession(LocalDate startDate, LocalDate endDate, Long longCover,
                                        SessionType sessionType, int studentCapacity) {
        return new Session(startDate, endDate, longCover, sessionType, studentCapacity);
    }

    public Long changeImageCover(Long changeCover) {
        this.imageId = changeCover;
        return imageId;
    }

    public SessionState recruitStudents() {
        sessionState = SessionState.RECRUITING;
        return sessionState;
    }

    public SessionState changeFinishSessionState(LocalDate date) {
        if (sessionDate.isAfterEndDate(date)) {
            sessionState = SessionState.FINISH;
        }
        return sessionState;
    }

    public int register() {
        validateSessionState();

        return studentCapacity.enroll();
    }

    private void validateSessionState() {
        if (!sessionState.equals(SessionState.RECRUITING)) {
            throw new IllegalArgumentException("모집 중이 아닙니다.");
        }
    }

    public int cancel() {
        return studentCapacity.cancel();
    }

    public SessionType changeSessionType(SessionType sessionType) {
        validateReadyState();
        this.sessionType = sessionType;
        return sessionType;
    }

    private void validateReadyState() {
        if (!sessionState.equals(SessionState.READY)) {
            throw new IllegalArgumentException("수정 기간이 지났습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getSessionState() {
        return sessionState.toString();
    }

    public String getSessionType() {
        return sessionType.toString();
    }

    public StudentCapacity getStudentCapacity() {
        return studentCapacity;
    }
}
