package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentRepository;
import nextstep.courses.exception.SessionNotFoundException;
import nextstep.courses.exception.SessionRegisterException;
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
        userRepository.findByUserId(userId)
                .ifPresentOrElse(
                        user -> {
                            Session savedSession = findById(sessionId);
                            savedSession.register(user);

                            Student student = savedSession.enrolledStudent(user);
                            studentRepository.save(student);
                        },
                        () -> {
                            throw new SessionRegisterException();
                        }
                );
    }

}
