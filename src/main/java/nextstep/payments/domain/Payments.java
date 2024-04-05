package nextstep.payments.domain;

import nextstep.courses.domain.ChargedSession;

import java.util.ArrayList;
import java.util.List;

public class Payments {
  private final List<Payment> payments = new ArrayList<>();

  public Payments() {
  }

  public Payments(final List<Payment> payments) {
    this.payments.addAll(payments);
  }

  public void add(final Payment payment) {
    this.payments.add(payment);
  }

  public void addAll(final List<Payment> payment) {
    this.payments.addAll(payment);
  }

  public boolean contains(final Payment payment) {
    return this.payments.contains(payment);
  }

  public boolean containsPaymentFor(final ChargedSession session) {
    return this.payments.stream()
            .anyMatch(payment -> payment.isPaymentFor(session));
  }
}
