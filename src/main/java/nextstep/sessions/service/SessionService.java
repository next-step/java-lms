package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;

public class SessionService {
  public void register(Session session, Payment payment) {
    session.register(payment);
  }
}
