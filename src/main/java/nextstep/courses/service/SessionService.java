package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionUserMappings;
import nextstep.courses.domain.Students;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcSessionUserMappingRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SessionService {
    private final JdbcSessionUserMappingRepository jdbcSessionUserMappingRepository;
    private final JdbcSessionRepository jdbcSessionRepository;
    private final JdbcUserRepository jdbcUserRepository;


    public SessionService(JdbcSessionUserMappingRepository jdbcSessionUserMappingRepository, JdbcSessionRepository jdbcSessionRepository, JdbcUserRepository jdbcUserRepository) {
        this.jdbcSessionUserMappingRepository = jdbcSessionUserMappingRepository;
        this.jdbcSessionRepository = jdbcSessionRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Transactional(readOnly = true)
    public Session findSession(Long sessionId) {
        Session session = jdbcSessionRepository.findById(sessionId);
        SessionUserMappings sessionUserMappings = new SessionUserMappings(jdbcSessionUserMappingRepository.findBySessionId(session.getId()));

        Students students = new Students(jdbcUserRepository.findByIds(sessionUserMappings.getNsUserIds()));
        students.convertStudentListToMap();
        session.changeStudents(students);

        return session;
    }

    @Transactional
    public int deleteSession(Session session) {
        int deleteSessionCount = jdbcSessionRepository.delete(session.getId());
        jdbcSessionUserMappingRepository.deleteBySessionId(session.getId());
        return deleteSessionCount;
    }

    @Transactional
    public void updateSession(Session session) {
        jdbcSessionRepository.update(session);
    }
}
