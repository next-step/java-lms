package nextstep.lms.service;

import nextstep.lms.AlreadyEnrolledException;
import nextstep.lms.domain.Session;
import nextstep.lms.domain.SessionRepository;
import nextstep.lms.domain.Student;
import nextstep.lms.domain.StudentRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("lmsService")
public class LmsService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "studentRepository")
    private StudentRepository studentRepository;

    @Transactional
    public Student enrollStudent(NsUser loginUser, Session session) throws AlreadyEnrolledException {
        studentRepository
                .findByNsUserIdAndSessionId(loginUser.getId(), session.getId())
                .ifPresent(s -> {throw new AlreadyEnrolledException();});

        Student student = session.enroll(loginUser);
        studentRepository.save(student);
        sessionRepository.updateRegisteredStudent(session);

        return student;
    }

    @Transactional
    public Student cancelStudent(NsUser loginUser, Session session) throws NotFoundException {
        Student student = studentRepository
                .findByNsUserIdAndSessionId(loginUser.getId(), session.getId())
                .orElseThrow(NotFoundException::new);

        Student canceledStudent = session.cancel(student);
        studentRepository.sessionCancel(canceledStudent);
        sessionRepository.updateRegisteredStudent(session);

        return student;
    }
}
