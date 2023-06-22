package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionUser;
import nextstep.courses.domain.Sessions;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcSessionUserMappingRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final JdbcSessionUserMappingRepository jdbcSessionUserMappingRepository;
    private final JdbcSessionRepository jdbcSessionRepository;
    private final JdbcUserRepository jdbcUserRepository;

    public UserService(JdbcSessionUserMappingRepository jdbcSessionUserMappingRepository, JdbcSessionRepository jdbcSessionRepository, JdbcUserRepository jdbcUserRepository) {
        this.jdbcSessionUserMappingRepository = jdbcSessionUserMappingRepository;
        this.jdbcSessionRepository = jdbcSessionRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Transactional
    public void registerSession(SessionUser sessionUser) {
        jdbcSessionUserMappingRepository.save(sessionUser);
    }

    @Transactional
    public int removeSession(SessionUser sessionUser) {
        return jdbcSessionUserMappingRepository.deleteByUserId(sessionUser.getNsUserId());
    }

    @Transactional
    public int updateSession(SessionUser sessionUser) {
        return jdbcSessionUserMappingRepository.update(sessionUser);
    }

    @Transactional(readOnly = true)
    public Sessions findSessionUser(Long nsUserId) {
        return new Sessions(jdbcSessionUserMappingRepository.findByUserId(nsUserId));

    }
}
