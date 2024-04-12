package nextstep.courses.service;

import nextstep.courses.domain.RegistrationRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CoursesService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  SessionRepository sessionRepository;

  @Autowired
  RegistrationRepository registrationRepository;

  @Transactional
  public void register(long userId, long sessionId) {
    userRepository.findByUserId(userId).ifPresentOrElse(
            user -> {
              Session session = sessionRepository.findById(sessionId);
              registrationRepository.save(user.register(session));
            },
            () -> {
              throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
            }
    );
  }
}
