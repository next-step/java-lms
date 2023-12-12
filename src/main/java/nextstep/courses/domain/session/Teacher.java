package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.Student;
import nextstep.users.domain.NsUser;

public class Teacher {

    private Long id;
    private NsUser nsUser;
    private Enrolment enrolment;

    public Teacher(Long id, NsUser nsUser, Enrolment enrolment) {
        this.id = id;
        this.nsUser = nsUser;
        this.enrolment = enrolment;
    }

    public Student select(Student student, SelectionStatus selectionStatus) {
        return enrolment.select(student, selectionStatus);
    }
}
