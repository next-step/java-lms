package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private Long id;
    private Long courseId;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private SessionFeeType sessionFeeType;

    public Session(Long id, Long courseId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionFeeType sessionFeeType) {
        this.id = id;
        this.courseId = courseId;
        StartDate = startDate;
        EndDate = endDate;
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
        this.sessionFeeType = sessionFeeType;
    }
}
