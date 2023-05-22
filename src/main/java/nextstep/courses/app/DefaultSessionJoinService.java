package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionJoinRepository;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultSessionJoinService implements SessionJoinService {
    private final SessionJoinRepository sessionJoinRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public DefaultSessionJoinService(SessionJoinRepository sessionJoinRepository, SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionJoinRepository = sessionJoinRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void register(long sessionId, List<String> userIds) {
        Session session = sessionRepository.findById(sessionId);
        List<NsUser> nsUsers = userRepository.findAllByUserIds(userIds);

        for (NsUser nsUser : nsUsers) {
            session.register(nsUser);
        }

        sessionJoinRepository.save(session);
    }

    @Override
    public void approve(long sessionId, List<String> userIds) {
        // TODO: 세션 있는지 여부 체크는 추후에..
        List<NsUser> nsUsers = userRepository.findAllByUserIds(userIds);
        List<Long> findUserIds = nsUsers.stream().map(NsUser::getId).collect(Collectors.toList());
        List<SessionJoin> sessionJoins = sessionJoinRepository.findAllBySessionIdAndUserIds(sessionId, findUserIds);

        for (SessionJoin sessionJoin : sessionJoins) {
            sessionJoin.approve();
            sessionJoinRepository.updateSessionJoinStatus(sessionJoin);
        }
    }

    @Override
    public void reject(long sessionId, List<String> userIds) {
        List<NsUser> nsUsers = userRepository.findAllByUserIds(userIds);
        List<Long> findUserIds = nsUsers.stream().map(NsUser::getId).collect(Collectors.toList());
        List<SessionJoin> sessionJoins = sessionJoinRepository.findAllBySessionIdAndUserIds(sessionId, findUserIds);

        for (SessionJoin sessionJoin : sessionJoins) {
            sessionJoin.reject();
            sessionJoinRepository.updateSessionJoinStatus(sessionJoin);
        }
    }
}
