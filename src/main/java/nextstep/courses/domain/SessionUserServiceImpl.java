package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void enroll(long sessionId, List<String> users) {
        // 기존 데이터들은 ApprovalStatus 정보가 없어서 ApprovalStatus.REQUEST(신청)로 마이그됨
        Session session = sessionRepository.findById(sessionId);
        List<NsUser> nsUsers = jdbcUserRepository.findByUserIds(users);
        nsUsers.forEach(session::enrollSession);
        sessionRepository.save(session);
    }

    @Override
    public void approve(long sessionId, List<String> users) {
        Session session = sessionRepository.findById(sessionId);
        SessionUsers sessionUsers = session.getSessionUsers();
        sessionUsers.getSessionUsers().forEach(SessionUser::approve);
        for (SessionUser sessionUser : sessionUsers.getSessionUsers()) {
            sessionUser.approve();
            sessionRepository.updateSessionApprovalStatus(sessionUser);
        }
    }

    @Override
    public void reject(long sessionId, List<String> users) {
        Session session = sessionRepository.findById(sessionId);
        SessionUsers sessionUsers = session.getSessionUsers();
        sessionUsers.getSessionUsers().forEach(SessionUser::approve);
        for (SessionUser sessionUser : sessionUsers.getSessionUsers()) {
            sessionUser.reject();
            sessionRepository.updateSessionApprovalStatus(sessionUser);
        }
    }
}
