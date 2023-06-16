package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentsRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;

import java.util.List;


public class SessionService {
    private JdbcUserRepository userRepository;
    private JdbcSessionRepository sessionRepository;
    private JdbcStudentsRepository studentsRepository;

    public Session findById(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        List<NsUser> students = studentsRepository.findAllBySessionId(sessionId);
        return new Session(session, students);
    }
}
