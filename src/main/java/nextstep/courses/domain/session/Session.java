package nextstep.courses.domain.session;

import nextstep.courses.domain.student.Student;
import nextstep.courses.exceptions.NotPeriodSessionException;
import nextstep.courses.exceptions.OverStudentException;

public class Session {

    private final SessionMandatoryInformation mandatoryInformation;

    private final SessionAdditionalInformation additionalInformation;

    public Session(SessionMandatoryInformation mandatoryInformation, SessionAdditionalInformation additionalInformation) {
        this.mandatoryInformation = mandatoryInformation;
        this.additionalInformation = additionalInformation;
    }

    //    public Session(SessionParticipant sessionParticipant, SessionCondition sessionsCondition, SessionTerm sessionTerm) {
//        this.sessionParticipant = sessionParticipant;
//        this.sessionsCondition = sessionsCondition;
//        this.sessionTerm = sessionTerm;
//    }

    public void apply(Student student) {
        mandatoryInformation.participate(student);
    }

    public int currentStudentNumber() {
        return mandatoryInformation.getStudent();
    }
}
