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
    private static final String ALREADY_ENROLLED_USER = "이미 등록한 사용자 입니다.";
    private final SessionRepository sessionRepository;
    private final JdbcUserRepository jdbcUserRepository;

    public SessionUserServiceImpl(SessionRepository sessionRepository, JdbcUserRepository jdbcUserRepository) {
        this.sessionRepository = sessionRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public void enroll(long sessionId, String user) {
        Session session = sessionRepository.findById(sessionId);
        List<SessionUser> sessionUsers = session.getSessionUsers().getSessionUsers();
        NsUser nsUser = jdbcUserRepository.findByUserId(user).orElse(NsUser.GUEST_USER);
        boolean isEnrolledUser = sessionUsers.stream().allMatch(sessionUser -> sessionUser.isIncludeNsUserId(nsUser));
        if (isEnrolledUser) {
            throw new IllegalArgumentException(ALREADY_ENROLLED_USER);
        } else {
            session.enrollSession(nsUser);
            sessionRepository.save(session);
        }
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
                .filter(SessionUser::isApproved)
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
