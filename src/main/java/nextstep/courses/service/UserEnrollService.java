package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userEnrollService")
public class UserEnrollService {

  @Transactional
  public void enrollBy(Session session, NsUser user) {
    session.enroll(user);
  }

  @Transactional
  public void enrollsBy(Session session, List<NsUser> users) {
    session.enroll(users);
  }

  @Transactional
  public Payment getPaidInfo(Session session, NsUser user) {
    return session.getPaymentInfo(user);
  }
}
