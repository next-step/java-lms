package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private final long id;
    private final SessionPeriod sessionPeriod;
    private final SessionInformation sessionInformation;
    private final SessionStudents sessionStudents;
    private final SessionEnrollment sessionEnrollment;
    private LocalDate registrationDate;

    private Session(
            long id,
            SessionPeriod sessionPeriod,
            SessionInformation sessionInformation,
            SessionStudents sessionStudents,
            SessionEnrollment sessionEnrollment,
            LocalDate registrationDate
    ) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.sessionInformation = sessionInformation;
        this.sessionStudents = sessionStudents;
        this.sessionEnrollment = sessionEnrollment;
        this.registrationDate = registrationDate;
    }


    public static Session of(
            SessionPeriod sessionPeriod,
            SessionInformation sessionInformation,
            SessionStudents sessionStudents,
            SessionEnrollment sessionEnrollment
    ) {
        return new Session(0, sessionPeriod, sessionInformation, sessionStudents, sessionEnrollment, LocalDate.now());
    }

    public static Session of(
            long id,
            SessionPeriod sessionPeriod,
            SessionInformation sessionInformation,
            SessionStudents sessionStudents,
            SessionEnrollment sessionEnrollment,
            LocalDate registrationDate
    ) {
        return new Session(id, sessionPeriod, sessionInformation, sessionStudents, sessionEnrollment, registrationDate);
    }

}
