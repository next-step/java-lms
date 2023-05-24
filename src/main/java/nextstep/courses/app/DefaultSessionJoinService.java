package nextstep.courses.app;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
        List<SessionJoin> sessionJoins = sessionJoinRepository.findAllBySessionId(sessionId);
        session.addSessionJoins(sessionJoins);
        List<NsUser> nsUsers = userRepository.findAllByUserIds(userIds);

        for (NsUser nsUser : nsUsers) {
            session.register(nsUser, SessionJoinStatus.APPLICATION);
        }

        sessionJoinRepository.save(session);
    }

    @Override
    public void approve(long sessionId, List<String> userIds) {
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
