package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.JdbcStudentsRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;


public class RegisterService {
    private JdbcUserRepository userRepository;
    private JdbcStudentsRepository studentsRepository;
    private SessionService sessionService;

    public void register(String userStringId, Long sessionId) {
        NsUser user = userRepository.findByUserId(userStringId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 userId"));
        Session session = sessionService.findById(sessionId);
        Long userId = session.add(user);
        studentsRepository.save(sessionId, userId);
    }
}
