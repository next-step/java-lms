package lms.domain;

import java.time.LocalDate;

import static lms.domain.SessionState.*;

public class Session {

    private LocalDate startDate;
    private LocalDate endDate;
    private Image imageCover;
    private SessionState sessionState = READY;

    public Session(LocalDate startDate, LocalDate endDate, Image imageCover) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageCover = imageCover;
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this(startDate, endDate, null);
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
}
