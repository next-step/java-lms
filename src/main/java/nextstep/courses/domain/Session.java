package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private final long id;
    private final int fixedNumberOfStudent;
    private final NsUser lecturer;
    private final LocalDateTime registrationDate;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Image imageCover;
    private SessionState sessionState = SessionState.PREPARING;
    private SessionType sessionType;
    private int numberOfStudentsRegistered;

    private Session(
            long id,
            int fixedNumberOfStudent,
            NsUser lecturer,
            LocalDateTime registrationDate,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Image imageCover,
            SessionState sessionState,
            SessionType sessionType,
            int numberOfStudentsRegistered
    ) {
        this.id = id;
        this.fixedNumberOfStudent = fixedNumberOfStudent;
        this.lecturer = lecturer;
        this.registrationDate = registrationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageCover = imageCover;
        this.sessionState = sessionState;
        this.sessionType = sessionType;
        this.numberOfStudentsRegistered = numberOfStudentsRegistered;
    }

}
