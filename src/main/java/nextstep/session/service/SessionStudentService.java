package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.student.SessionStudent;
import nextstep.session.domain.student.SessionStudentRepository;
import nextstep.session.domain.student.SessionStudentStatus;
import nextstep.session.domain.student.SessionStudents;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionStudentService {
    private SessionStudentRepository studentRepository;

    public SessionStudentService(SessionStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Long enrollStudent(Session session, NsUser nsUser) {
        SessionStudent student = session.enrollStudent(nsUser);
        return studentRepository.enrollStudent(session.getId(), student.getId());
    }

    @Transactional
    public int approveStudent(SessionStudent student) {
        return studentRepository.changeStudentStatus(student.getId(), SessionStudentStatus.APPROVE);
    }

    @Transactional
    public int refuseStudent(SessionStudent student) {
        return studentRepository.changeStudentStatus(student.getId(), SessionStudentStatus.REFUSAL);
    }

    public SessionStudents getStudentsOfSession(Session session) {
        List<SessionStudent> students = studentRepository.getStudents(session.getId());
        return new SessionStudents(students, session.getSessionStudents().getMaximumCapacity());
    }
}
