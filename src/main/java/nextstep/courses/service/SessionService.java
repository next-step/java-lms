package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Student;
import nextstep.courses.repository.CandidateRepository;
import nextstep.courses.repository.SessionRepository;
import nextstep.courses.repository.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class SessionService {

    private final SessionRepository sessionRepository;

    private final StudentRepository studentRepository;

    private final CandidateRepository candidateRepository;

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository, CandidateRepository candidateRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public void registerSession(Long sessionId, NsUser nsUser) {
        Session registered = sessionRepository.findById(sessionId)
                .initUsers(studentRepository.findBySessionId(sessionId))
                .validateRegister(nsUser);
        studentRepository.registerSession(registered.getId(), nsUser.getId());
    }

    @Transactional
    public void approveSessionRegister(Long sessionId, NsUser nsUser) {
        Student student = sessionRepository.findById(sessionId)
                .initUsers(studentRepository.findBySessionId(sessionId))
                .initCandidates(candidateRepository.findBySessionId(sessionId))
                .approve(nsUser);
        studentRepository.approve(student);
    }

    @Transactional
    public void rejectSessionRegister(Long sessionId, NsUser nsUser) {
        Student student = sessionRepository.findById(sessionId)
                .initUsers(studentRepository.findBySessionId(sessionId))
                .initCandidates(candidateRepository.findBySessionId(sessionId))
                .reject(nsUser);
        studentRepository.reject(student);
    }
}
