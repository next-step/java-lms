package nextstep.courses.service;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CourseService {
  @Autowired
  private RegistrationRepository registrationRepository;

  @Transactional
  public void approve(Long userId, Long sessionId) {
    Registration registration = registrationRepository.findByUserIdAndSessionId(userId, sessionId);
    registration.approve();
    registrationRepository.save(registration);
  }

  @Transactional
  public void reject(Long userId, Long sessionId) {
    Registration registration = registrationRepository.findByUserIdAndSessionId(userId, sessionId);
    registration.reject();
    registrationRepository.save(registration);
  }
}
