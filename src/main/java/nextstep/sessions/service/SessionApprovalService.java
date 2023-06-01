package nextstep.sessions.service;

import nextstep.sessions.SessionNotFoundException;
import nextstep.sessions.UserNotFoundException;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.students.domain.Student;
import nextstep.students.domain.StudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionApprovalService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public SessionApprovalService(SessionRepository sessionRepository,
                                  UserRepository userRepository,
                                  StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public Session findSessionById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public NsUser findUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public void approve(String userId, Long sessionId) {
        NsUser user = findUserByUserId(userId);

        Session savedSession = findSessionById(sessionId);

        List<Student> appliedStudents = studentRepository.findAllBySessionId(sessionId);
        savedSession.addStudents(appliedStudents);
        savedSession.approved(user);

        Student student = savedSession.enrolledStudent(user);

        studentRepository.update(student);
    }

    public void reject(String userId, Long sessionId) {
        NsUser user = findUserByUserId(userId);
        Session savedSession = findSessionById(sessionId);
        List<Student> appliedStudents = studentRepository.findAllBySessionId(sessionId);
        Student rejectedStudent = savedSession.rejected(user, appliedStudents);
        studentRepository.update(rejectedStudent);
    }

}
