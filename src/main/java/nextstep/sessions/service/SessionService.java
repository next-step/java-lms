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

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public SessionService(SessionRepository sessionRepository,
                          UserRepository userRepository,
                          StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public long save(Session session) {
        return sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException(id));
    }

    public void register(String userId, Long sessionId) {
        NsUser user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        Session savedSession = findById(sessionId);
        savedSession.register(user);

        Student student = savedSession.enrolledStudent(user);
        studentRepository.save(student);
    }

}
