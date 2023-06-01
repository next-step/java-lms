package nextstep.courses.domain.session;

import nextstep.courses.domain.student.Student;

public class Session {

    private final Long id;

    private final SessionRequired required;

    private final SessionOptional optional;

    public Session(Long id, SessionRequired mandatoryInformation, SessionOptional additionalInformation) {
        this.id = id;
        this.required = mandatoryInformation;
        this.optional = additionalInformation;
    }

    public void apply(Student student) {
        required.participate(student);
    }

    public int currentStudentNumber() {
        return required.getStudent();
    }

    public int getMaxStudentCount() {
        return required.getMaximumStudent();
    }
}
