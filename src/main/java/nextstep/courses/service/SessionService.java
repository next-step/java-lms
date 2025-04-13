package nextstep.courses.service;

import nextstep.courses.domain.Session;

public class SessionService {
  public void register(Session session, nextstep.payments.domain.Payment payment) {
    session.register(payment);
  }
}
