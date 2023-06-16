package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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


}
