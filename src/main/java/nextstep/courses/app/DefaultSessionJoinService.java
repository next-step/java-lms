package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionJoinRepository;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultSessionJoinService implements SessionJoinService{
    private final SessionJoinRepository sessionJoinRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;


    public DefaultSessionJoinService(SessionJoinRepository sessionJoinRepository, SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionJoinRepository = sessionJoinRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void register(long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId);
        Optional<NsUser> nsUserOptional = userRepository.findByUserId(nsUser.getUserId());

        if (nsUserOptional.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 ID 입니다.");
        }

        session.register(nsUserOptional.get());

        sessionJoinRepository.save(session);
    }

    @Override
    public void approve(long sessionId, NsUser nsUser) {
        Optional<NsUser> nsUserOptional = userRepository.findByUserId(nsUser.getUserId());

        if (nsUserOptional.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 ID 입니다.");
        }

        SessionJoin sessionJoin = sessionJoinRepository.findBySessionIdAndUserId(sessionId, nsUser.getId());
        sessionJoin.approve();
        sessionJoinRepository.updateSessionJoinStatus(sessionJoin);
    }

    @Override
    public void reject(long sessionId, NsUser nsUser) {
        Optional<NsUser> nsUserOptional = userRepository.findByUserId(nsUser.getUserId());

        if (nsUserOptional.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 ID 입니다.");
        }
        SessionJoin sessionJoin = sessionJoinRepository.findBySessionIdAndUserId(sessionId, nsUser.getId());

        sessionJoin.reject();
        sessionJoinRepository.updateSessionJoinStatus(sessionJoin);
    }

}
