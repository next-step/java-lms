package lms.domain;

import java.time.LocalDate;

import static lms.domain.SessionState.*;
import static lms.domain.SessionType.*;

public class Session {

    private LocalDate startDate;
    private LocalDate endDate;
    private Image imageCover;
    private SessionState sessionState = READY;
    private SessionType sessionType;
    private int studentCapacity;
    private int registeredStudent = 0;

    public Session(LocalDate startDate, LocalDate endDate, Image imageCover, SessionType sessionType, int studentCapacity) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageCover = imageCover;
        this.sessionType = sessionType;
        this.studentCapacity = studentCapacity;
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this(startDate, endDate, null, FREE, 0);
    }

    public Image changeImageCover(Image changeCover) {
        this.imageCover = changeCover;
        return imageCover;
    }

    public SessionState recruitStudents() {
        sessionState = RECRUITING;
        return sessionState;
    }

    public SessionState finishSessionState(LocalDate now) {
        if (now.isAfter(endDate)) {
            sessionState = FINISH;
        }
        return sessionState;
    }

    public int register() {
        ++registeredStudent;

        validateSessionState();
        validateCapacity();

        return registeredStudent;
    }

    private void validateSessionState() {
        if (!sessionState.equals(RECRUITING)) {
            throw new IllegalArgumentException("모집 중이 아닙니다.");
        }
    }

    private void validateCapacity() {
        if (registeredStudent > studentCapacity) {
            throw new IllegalArgumentException("수강 인원이 다 찼습니다.");
        }
    }

    public int cancel() {
        return --registeredStudent;
    }

    public SessionType changeSessionType(SessionType sessionType) {
        if (!sessionState.equals(READY)) {
            throw new IllegalArgumentException("수정 기간이 지났습니다.");
        }
        this.sessionType = sessionType;
        return sessionType;
    }
}
