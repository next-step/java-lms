package nextstep.courses.domain;

import java.time.LocalDate;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class Session {

    private int generation;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionType type;
    private SessionStatus status;
    private int maxNumOfStudent;

    public Session(int generation, LocalDate startDate, LocalDate endDate, SessionType type, SessionStatus status, int maxNumOfStudent) {
        this.generation = generation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.maxNumOfStudent = maxNumOfStudent;
    }

    boolean isRecruiting() {
        return RECRUITING == status;
    }
}
