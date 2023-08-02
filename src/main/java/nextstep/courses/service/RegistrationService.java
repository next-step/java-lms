package nextstep.courses.service;

import java.util.Optional;
import nextstep.courses.SessionApprovalFailException;
import nextstep.courses.SessionCancelFailException;
import nextstep.courses.domain.application.Application;
import nextstep.courses.domain.application.ApplicationRepository;
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

  private final ApplicationRepository applicationRepository;
  private final SessionRepository sessionRepository;
  private final RegistrationRepository registrationRepository;

  public RegistrationService(
      ApplicationRepository applicationRepository,
      SessionRepository sessionRepository,
      RegistrationRepository registrationRepository) {
    this.applicationRepository = applicationRepository;
    this.sessionRepository = sessionRepository;
    this.registrationRepository = registrationRepository;
  }

  @Transactional
  public void register(NsUser loginUser, long sessionId) {
    Session session = findSession(sessionId);
    Registrations registrations = new Registrations(
        registrationRepository.findBySessionId(sessionId));

    Registration registration = Registration.createRegistration(loginUser, session, registrations);
    registrationRepository.save(registration);
  }

  @Transactional
  public void approve(long registrationId) {
    Registration registration = findRegistration(registrationId);
    Session session = findSession(registration.getSessionId());
    verifyApprove(registration, session);

    registration.approval();
    registrationRepository.save(registration);
  }

  private void verifyApprove(Registration registration, Session session) {
    Optional<Application> optionalApplication = findApplicationOptional(registration, session);
    Application application = optionalApplication
        .orElseThrow(() -> new SessionApprovalFailException("강의에 해당하는 코스 지원자가 아닙니다."));

    verifyPassStatus(application);
  }

  private void verifyPassStatus(Application application) {
    if (!application.isPass()) {
      throw new SessionApprovalFailException("강의에 해당하는 코스 선발생이 아닙니다.");
    }
  }

  @Transactional
  public void cancel(long registrationId) {
    Registration registration = findRegistration(registrationId);
    Session session = findSession(registration.getSessionId());
    verifyCancel(registration, session);

    registration.cancel();
    registrationRepository.save(registration);
  }

  private void verifyCancel(Registration registration, Session session) {
    Optional<Application> optionalApplication = findApplicationOptional(registration, session);
    if (optionalApplication.isEmpty()) {
      return;
    }

    verifyFailStatus(optionalApplication.get());
  }

  private void verifyFailStatus(Application application) {
    if (application.isPass()) {
      throw new SessionCancelFailException("강의에 해당하는 코스 선발생 입니다.");
    }
  }

  private Session findSession(long sessionId) {
    return sessionRepository.findById(sessionId)
        .orElseThrow(NotFoundException::new);
  }

  private Registration findRegistration(long registrationId) {
    return registrationRepository.findById(registrationId)
        .orElseThrow(NotFoundException::new);
  }

  private Optional<Application> findApplicationOptional(Registration registration,
      Session session) {
    Optional<Application> optionalApplication = applicationRepository
        .findByNsUserIdAndCourseId(registration.getNsUserId(), session.getCourseId());
    return optionalApplication;
  }
}
