package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

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
  @DisplayName("결제 금액이 일치하지 않으면 등록할 수 없다.")
  void register_fail_when_amount_invalid() {
    EnrollmentManager manager = new EnrollmentManager(new PaidPolicy(5, 10000L));
    manager.updateStatus(SessionStatus.OPEN);

    Payment payment = new Payment("p2", 1L, 102L, 8000L);

    assertThatIllegalArgumentException()
        .isThrownBy(() -> manager.register(payment))
        .withMessageContaining("결제 금액");
  }

  @Test
  @DisplayName("정원이 초과되면 등록할 수 없다.")
  void register_fail_when_exceed_limit() {
    EnrollmentManager manager = new EnrollmentManager(new PaidPolicy(1, 10000L));
    manager.updateStatus(SessionStatus.OPEN);

    // 첫 번째 등록 성공
    manager.register(new Payment("p3", 1L, 201L, 10000L));

    // 두 번째 등록은 실패
    Payment second = new Payment("p4", 1L, 202L, 10000L);

    assertThatIllegalStateException()
        .isThrownBy(() -> manager.register(second))
        .withMessageContaining("정원 초과");
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
