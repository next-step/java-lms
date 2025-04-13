package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidPolicy implements Policy {
  private final int max;
  private final long fee;

  public PaidPolicy(int max, long fee) {
    this.max = max;
    this.fee = fee;
  }

  @Override
  public void validate(Payment payment, int currentParticipants) {
    if (currentParticipants >= max) {
      throw new IllegalStateException("정원 초과입니다.");
    }
    if (!payment.amount().equals(fee)) {
      throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
    }
  }
}
