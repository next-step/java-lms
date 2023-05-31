package nextstep.courses.domain.session;

import nextstep.courses.domain.student.Student;
import nextstep.courses.exceptions.NotPeriodSessionException;
import nextstep.courses.exceptions.OverStudentException;

public class SessionRequired {

    private final SessionParticipant sessionParticipant;

    private final SessionCondition sessionsCondition;

    private final SessionTerm sessionTerm;

    public SessionRequired(SessionParticipant sessionParticipant, SessionCondition sessionsCondition,
            SessionTerm sessionTerm) {
        this.sessionParticipant = sessionParticipant;
        this.sessionsCondition = sessionsCondition;
        this.sessionTerm = sessionTerm;
    }

    private void validateEnroll() {
        if (!sessionsCondition.participateAvailable()) {
            throw new NotPeriodSessionException();
        }

        if (sessionParticipant.isFullSession()) {
            throw new OverStudentException();
        }

    }

    public void participate(Student student) {
        validateEnroll();
        sessionParticipant.participateStudent(student);
    }

    public int getStudent() {
        return sessionParticipant.getStudentsCount();
    }
}
