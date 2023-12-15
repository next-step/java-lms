package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

    private Long id;

    private String name;

    private SessionStatus sessionStatus;

    private SessionType sessionType;

    private LocalDate startDt;

    private LocalDate endDt;

    public Session(Long id, String name, SessionStatus sessionStatus, SessionType sessionType,
                   LocalDate startDt, LocalDate endDt) {
        this.id = id;
        this.name = name;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    public boolean isRecruitingStatus() {
        return sessionStatus.equals(SessionStatus.RECRUITING);
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public SessionStatus sessionStatus() {
        return sessionStatus;
    }

    public SessionType sessionType() {
        return sessionType;
    }

    public LocalDate startDt() {
        return startDt;
    }

    public LocalDate endDt() {
        return endDt;
    }

    public boolean isPossibleToRegister(Long paidAmount, int enrolledStudents) {
        return this.sessionType.isPossibleToRegister(paidAmount, enrolledStudents);
    }
}
