package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.exceptions.NotPeriodSessionException;
import nextstep.courses.exceptions.OverStudentException;

public class Session {

    private final SessionParticipant sessionParticipant;

    private final SessionCondition sessionsCondition;

    private final SessionTerm sessionTerm;

    public Session(SessionParticipant sessionParticipant, SessionCondition sessionsCondition, SessionTerm sessionTerm) {
        this.sessionParticipant = sessionParticipant;
        this.sessionsCondition = sessionsCondition;
        this.sessionTerm = sessionTerm;
    }

    public void enroll() {
        if (!sessionsCondition.participateAvailable()) {
            throw new NotPeriodSessionException();
        }

        if (sessionParticipant.isFullSession()) {
            throw new OverStudentException();
        }

        participate();
    }

    private void participate() {
        sessionParticipant.participateStudent();
    }

    public int getStudent() {
        return sessionParticipant.getStudents();
    }
}
