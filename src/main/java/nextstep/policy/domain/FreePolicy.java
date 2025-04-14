package nextstep.policy.domain;

import nextstep.payments.domain.Payment;

public class FreePolicy implements Policy {

  @Override
  public void validate(Payment payment, int currentParticipants) {
    // nothing to validate
  }
}
