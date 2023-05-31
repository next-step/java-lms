package nextstep.courses.domain.session;

import nextstep.courses.domain.student.Student;

public class Session {

    private final SessionRequired mandatoryInformation;

    private final SessionOptional additionalInformation;

    public Session(SessionRequired mandatoryInformation, SessionOptional additionalInformation) {
        this.mandatoryInformation = mandatoryInformation;
        this.additionalInformation = additionalInformation;
    }

    public void apply(Student student) {
        mandatoryInformation.participate(student);
    }

    public int currentStudentNumber() {
        return mandatoryInformation.getStudent();
    }
}
