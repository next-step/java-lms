package nextstep.courses.domain;

import java.time.LocalDate;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class Session {

    private String title;
    private int generation;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionType type;
    private SessionStatus status;
    private int maxRegisterNum;
    private int currRegisterNum;

    public Session(String title, int generation, LocalDate startDate, LocalDate endDate, SessionType type, SessionStatus status, int maxRegisterNum, int currRegisterNum) {
        this.title = title;
        this.generation = generation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.maxRegisterNum = maxRegisterNum;
        this.currRegisterNum = maxRegisterNum;
    }

    boolean isRecruiting() {
        return RECRUITING == status;
    }

    public String title() {
        return title;
    }


}
