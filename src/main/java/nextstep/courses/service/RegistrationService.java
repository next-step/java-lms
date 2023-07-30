package nextstep.courses.service;

import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("registrationService")
public class RegistrationService {

  private SessionRepository sessionRepository;

  private RegistrationRepository registrationRepository;

  @Transactional
  public void registerSession(NsUser loginUser, long sessionId) {
    Session session = sessionRepository.findById(sessionId)
        .orElseThrow(NotFoundException::new);
    Registrations registrations = new Registrations(
        registrationRepository.findBySessionId(sessionId));

    Registration registration = Registration.createRegistration(loginUser, session, registrations);
    registrationRepository.save(registration);
  }

  @Transactional
  public void cancelRegistration(long registrationId) {
    Registration registration = registrationRepository.findById(registrationId)
        .orElseThrow(NotFoundException::new);

    registration.cancel();
    registrationRepository.save(registration);
  }
}
