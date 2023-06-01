package nextstep.courses.domain;

import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final JdbcUserRepository jdbcUserRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, JdbcUserRepository jdbcUserRepository) {
        this.sessionRepository = sessionRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public long save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    @Transactional(readOnly = true)
    public Session findById(long id) {
        return sessionRepository.findById(id);
    }

}
