package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NextStepUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {

  private final SessionRepository sessionRepository;

  public SessionService(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @Transactional
  public void enrollUsers(Session session, List<NextStepUser> nextStepUsers) {
    nextStepUsers.forEach(session::processEnrollment);

    sessionRepository.saveAllSessionUser(session);
  }
}
