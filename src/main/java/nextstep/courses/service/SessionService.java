package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NextStepUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

  private final SessionRepository sessionRepository;

  public SessionService(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  public void enrollUsers(Long sessionId, List<NextStepUser> nextStepUsers) {
    Session session = sessionRepository.findById(sessionId);

    nextStepUsers.forEach(nextStepUser -> {
      session.processEnrollment(nextStepUser);

      sessionRepository.saveSessionUser(session, nextStepUser);
    });
  }
}
