package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import nextstep.payments.FreePolicy;
import nextstep.payments.PaidPolicy;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentManagerTest {
  @Test
  @DisplayName("모집중 상태가 아니면 등록할 수 없다.")
  void register_fail_when_status_not_open() {
    EnrollmentManager manager = new EnrollmentManager(new FreePolicy());

    Payment payment = new Payment("p1", 1L, 101L, 0L);

    assertThatIllegalStateException()
        .isThrownBy(() -> manager.register(payment))
        .withMessageContaining("모집중인 강의만");
  }

  @Test
  @DisplayName("모집중 상태이고 정책을 만족하면 등록된다.")
  void register_success() {
    EnrollmentManager manager = new EnrollmentManager(new PaidPolicy(3, 10000L));
    manager.updateStatus(SessionStatus.OPEN);

    Payment payment = new Payment("p5", 1L, 301L, 10000L);

    manager.register(payment);

    assertThat(manager.count()).isEqualTo(1);
  }

}
