package nextstep.sessions.domain;

import nextstep.images.domain.Image;

import java.time.LocalDateTime;

public class FreeSession extends Session{
    private int studentCount;

    public FreeSession(Long id, LocalDateTime startedDate, LocalDateTime endedDate, Image image, SessionType sessionType) {
        super(id, startedDate, endedDate, image, sessionType);
        this.studentCount = INIT_COUNT;
    }

    public void enrolment() {
        isEnrolmentPossible();
        this.studentCount++;
    }
}
