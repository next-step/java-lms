package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private int generation;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionType type;
    private SessionStatus status;
    private int maxNumOfStudent;
}
