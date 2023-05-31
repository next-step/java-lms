package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionUserServiceImpl implements SessionUserService {
    private final SessionRepository sessionRepository;
    private final JdbcUserRepository jdbcUserRepository;

    public SessionUserServiceImpl(SessionRepository sessionRepository, JdbcUserRepository jdbcUserRepository) {
        this.sessionRepository = sessionRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public void enroll(long sessionId, String user) {
        Session session = sessionRepository.findById(sessionId);
        NsUser nsUser = jdbcUserRepository.findByUserId(user).orElse(NsUser.GUEST_USER);
        session.enrollSession(nsUser);
        sessionRepository.saveSessionUser(session);
        sessionRepository.save(session);
    }

    @Override
    public void approve(long sessionId, List<String> users) {
        Session session = sessionRepository.findById(sessionId);
        int maxEnrollment = session.getSessionUsers().getMaxEnrollment();
        List<NsUser> nsUsers = jdbcUserRepository.findByUserIds(users);
        List<Long> userIds = nsUsers.stream().map(NsUser::getId).collect(Collectors.toList());
        List<SessionUser> sessionUserList = sessionRepository.findAllBySessionIdAndUserIds(sessionId, userIds);
        SessionUsers sessionUsers = new SessionUsers(sessionUserList, maxEnrollment);

        sessionUsers.getSessionUsers()
                .stream()
                .filter(SessionUser::isRequested)
                .collect(Collectors.toList())
                .forEach(sessionUser -> {
                    sessionUser.approve();
                    sessionRepository.updateSessionApprovalStatus(sessionUser);
                });
    }

    @Override
    public void reject(long sessionId, List<String> users) {
        List<NsUser> nsUsers = jdbcUserRepository.findByUserIds(users);
        List<Long> userIds = nsUsers.stream().map(NsUser::getId).collect(Collectors.toList());
        List<SessionUser> sessionUsers = sessionRepository.findAllBySessionIdAndUserIds(sessionId, userIds);

        sessionUsers.forEach(sessionUser -> {
            sessionUser.reject();
            sessionRepository.updateSessionApprovalStatus(sessionUser);
        });
    }
}
