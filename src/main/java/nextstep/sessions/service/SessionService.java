package nextstep.sessions.service;

import nextstep.sessions.domain.Session;
import nextstep.sessions.infrastructure.JdbcSessionRepository;

public class SessionService {

    private final JdbcSessionRepository jdbcSessionRepository;

    public SessionService(JdbcSessionRepository jdbcSessionRepository) {
        this.jdbcSessionRepository = jdbcSessionRepository;
    }

    public Session findById(Long id) {
        return jdbcSessionRepository.findById(id);
    }
}
