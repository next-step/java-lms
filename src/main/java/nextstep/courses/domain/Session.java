package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;

import java.time.LocalDateTime;

abstract public class Session {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final SessionStatus sessionStatus;
    private int numberOfStudents;
    private CoverImageInfo coverImageInfo;

    protected Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionStatus = SessionStatus.READY;
        this.numberOfStudents = 0;
    }
}
