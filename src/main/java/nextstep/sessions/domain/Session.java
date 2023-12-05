package nextstep.sessions.domain;

import nextstep.images.domain.Image;
import nextstep.sessions.strategy.EnrolmentStrategy;

import java.time.LocalDateTime;

public abstract class Session {
    public static final int INIT_COUNT = 0;

    private final Long id;
    private final LocalDateTime startedDate;
    private final LocalDateTime endedDate;
    private final Image image;
    private Status status;
    private final SessionType sessionType;

    public Session(Long id, LocalDateTime startedDate, LocalDateTime endedDate, Image image, SessionType sessionType) {
        validateDate(startedDate, endedDate);
        this.id = id;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
        this.image = image;
        this.status = getSessionStatus(startedDate, endedDate);
        this.sessionType = sessionType;
    }

    private void validateDate(LocalDateTime startedDate, LocalDateTime endedDate) {
        if (startedDate.isAfter(endedDate)) {
            throw new IllegalArgumentException("강의 시작일은 종료일을 넘을 수 없습니다.");
        }
    }

    private Status getSessionStatus(LocalDateTime startedDate, LocalDateTime endedDate) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startedDate)) {
            return Status.PREPARE;
        }
        if (now.isAfter(endedDate)) {
            return Status.CLOSED;
        }
        return Status.RECRUITING;
    }

    public void changeSessionStatus(Status status) {
        this.status = status;
    }

    public void isEnrolmentPossible(){
        if (!status.equals(Status.RECRUITING)) {
            throw new IllegalArgumentException("강의가 모집중이 아닙니다.");
        }
    }

}
