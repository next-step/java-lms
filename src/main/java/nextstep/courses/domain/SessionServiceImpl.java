package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public long enroll(long id, List<String> users) {
        Session session = findById(id);
        List<NsUser> nsUsers = jdbcUserRepository.findByUserIds(users);
        nsUsers.forEach(session::enrollSession);
        return sessionRepository.save(session);
    }

}
