package nextstep.courses.service;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.RegistrationState;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import nextstep.users.domain.NsUser;

public class EnrollSessionService {
    public Student enrollSession(Session session, NsUser student) {
        Student studentInfo = new Student(student.getId(), session.getId(), RegistrationState.PENDING);
        if (SessionType.isFree(session.getSessionType())) {
            session.signUp(studentInfo);
        } else {
            session.signUp(studentInfo);
        }
        return studentInfo;
    }

    public void registerStudent(Session session, Student student, Boolean approved) {
        if (approved) {
            session.approveStudent(student);
        } else {
            session.cancelStudent(student);
        }

    }
}
