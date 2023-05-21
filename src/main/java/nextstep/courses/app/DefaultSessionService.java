package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultSessionService implements SessionService {
    private final SessionRepository sessionRepository;

    public DefaultSessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
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
    public long register(Session session, List<NsUser> nsUsers) {
        for (NsUser nsUser : nsUsers) {
            session.register(nsUser);
        }

        return sessionRepository.saveSessionJoin(session);
    }
}
