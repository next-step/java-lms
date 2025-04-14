package nextstep.policy.domain;

import nextstep.payments.domain.Payment;

public interface Policy {
  void validate(Payment payment, int currentParticipants);
}
