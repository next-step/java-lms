package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionJoinRepository;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
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
        NsUser nsUserOpt = userRepository.findByUserId(nsUser.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 ID 입니다."));

        SessionJoin sessionJoin =  session.register(nsUserOpt);

        sessionJoinRepository.save(sessionJoin);
    }

    @Override
    public void approve(long sessionId, NsUser nsUser) {
        NsUser nsUserOpt = userRepository.findByUserId(nsUser.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 ID 입니다."));

        SessionJoin sessionJoin = sessionJoinRepository.findBySessionIdAndUserId(sessionId, nsUserOpt.getId());
        sessionJoin.approve();
        sessionJoinRepository.updateSessionJoinStatus(sessionJoin);
    }

    @Override
    public void reject(long sessionId, NsUser nsUser) {
        NsUser nsUserOpt = userRepository.findByUserId(nsUser.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 ID 입니다."));

        SessionJoin sessionJoin = sessionJoinRepository.findBySessionIdAndUserId(sessionId, nsUserOpt.getId());

        sessionJoin.reject();
        sessionJoinRepository.updateSessionJoinStatus(sessionJoin);
    }

}
