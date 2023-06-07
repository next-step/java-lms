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
    private final UserRepository userRepository;


    public DefaultSessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
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
    public long register(long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId);
        Optional<NsUser> nsUserOptional = userRepository.findByUserId(nsUser.getUserId());

        if (nsUserOptional.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 ID 입니다.");
        }

        session.register(nsUserOptional.get());

        return sessionRepository.saveSessionJoin(session);
    }

}
