package nextstep.courses.service;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.RegistrationRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;


public class SessionService {
    private final RegistrationRepository registrationRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public SessionService(RegistrationRepository registrationRepository, SessionRepository sessionRepository, UserRepository userRepository) {
        this.registrationRepository = registrationRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public void registerSession(String userId, Long sessionId, Long amount) {
        NsUser user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유효한 userId가 아닙니다."));
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("유효한 sessionId가 아닙니다."));;

        registrationRepository.save(Registration.register(user, session, amount));
    }
}
