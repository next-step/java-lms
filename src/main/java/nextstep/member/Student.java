package nextstep.member;

import nextstep.payments.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class Student {
  private final Long id;
  private final List<Payment> payments = new ArrayList<>();

  public Student(Long id) {
    this.id = id;
  }

  public Student(Long id, final List<Payment> payments) {
    this.id = id;
    this.payments.addAll(payments);
  }
}
