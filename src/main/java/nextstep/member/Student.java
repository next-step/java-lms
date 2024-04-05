package nextstep.member;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.ChargedSession;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;

import java.util.List;

public class Student {
  private final Long id;
  private final Payments payments = new Payments();

  public Student(Long id) {
    this.id = id;
  }

  public Student(Long id, final List<Payment> payments) {
    this.id = id;
    this.payments.addAll(payments);
  }

  public boolean notHasPaymentFor(ChargedSession session) {
    return !this.payments.containsPaymentFor(session);
  }

  public void register(final Session session) {
    session.addStudent(this);
  }
}
