package nextstep.courses.service;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.Session;

public class EnrollSessionService {
    public void registerStudent(Session session, Student student, Boolean approved) {
        if (approved) {
            session.approveStudent(student);
        } else {
            session.cancelStudent(student);
        }

    }
}
