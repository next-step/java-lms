package nextstep.courses.domain;

import java.nio.file.attribute.AttributeView;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class EnrollmentManager {
  private final Policy policy;
  private final NsUsers users = new NsUsers();
  private SessionStatus status = SessionStatus.READY;

  public EnrollmentManager(Policy policy) {
    this.policy = policy;
  }

  public void register(nextstep.payments.domain.Payment payment) {
    if (status != SessionStatus.OPEN) {
      throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
    }
    policy.validate(payment, users.count());
    users.add(new NsUser(payment.nsUserId(), null, null, null, null));
  }

  public void updateStatus(SessionStatus status) {
    this.status = status;
  }

  public int count() {
    return users.count();
  }

  public Policy policy() {
    return policy;
  }

  public SessionStatus status() {
    return status;
  }
}
