package nextstep.courses.application;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

/**
 * 수강 신청 서비스
 */
@Service
public class SessionSignUpService {

  private final SessionRepository sessionRepository;
  private final UserRepository userRepository;

  public SessionSignUpService(SessionRepository sessionRepository, UserRepository userRepository) {
    this.sessionRepository = sessionRepository;
    this.userRepository = userRepository;
  }

  public Long signUp(Long sessionId, String userId) {
    Session session = sessionRepository.findById(sessionId);

    NsUser user = userRepository.findByUserId(userId);

    session.enroll(user);

    return sessionRepository.saveSignUpHistory(sessionId, user.getId());
  }

}
