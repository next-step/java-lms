package nextstep.session.service;

import javax.annotation.Resource;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.session.domain.Student;
import nextstep.session.domain.Students;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionStudentService")
public class SessionStudentService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "sessionStudentRepository")
    private SessionStudentRepository sessionStudentRepository;

    @Transactional
    public Students getEnrolledStudents(Long id) {
        return new Students(sessionStudentRepository.findAllEnrolledInSession(id));
    }

    @Transactional
    public Students findAllApprovedStudents(Long id) {
        return new Students(sessionStudentRepository.findAllApprovedStudents(id));
    }

    @Transactional
    public void approveStudent(Long id, Student student) {
        Session session = sessionRepository.findById(id);
        session.approvalStudent(student);
        sessionStudentRepository.updateStatus(student);
    }

    @Transactional
    public void cancelStudent(Long id, Student student) {
        Session session = sessionRepository.findById(id);
        session.cancelStudent(student);
        sessionStudentRepository.updateStatus(student);
    }


}
