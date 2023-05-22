package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private Long id;

    private SessionCoverImage sessionCoverImage;

    private SessionPeriod sessionPeriod;

    private SessionPaymentType sessionPaymentType;

    private SessionStatus sessionStatus;

    private SessionStudents sessionStudents = new SessionStudents();

    public Session(SessionPeriod sessionPeriod, SessionPaymentType sessionPaymentType, SessionStatus sessionStatus) {
        this.sessionPeriod = sessionPeriod;
        this.sessionPaymentType = sessionPaymentType;
        this.sessionStatus = sessionStatus;
    }

    public void enrollSession() {
        sessionPeriod.includeCurrentDate(LocalDate.now());
        sessionStatus.enableEnrollment(sessionStatus);
        sessionStudents.addStudent();
    }

    public int getNumberOfStudent() {
        return this.sessionStudents.getNumberOfStudents();
    }
}
