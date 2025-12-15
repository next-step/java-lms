package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentApplicationTest {
    @Test
    public void 신청서를_생성한다() {
        Payment payment = new Payment("결제번호-1", 1L, 1L, 50_000L);

        EnrollmentApplication application = new EnrollmentApplication(1L, 1L, payment);

        assertThat(application.getSessionId()).isEqualTo(1L);
        assertThat(application.getNsUserId()).isEqualTo(1L);
        assertThat(application.getStatus()).isEqualTo(EnrollmentStatus.PENDING);
    }

    @Test
    public void 신청서를_승인한다() {
        EnrollmentApplication application = new EnrollmentApplication(1L, 1L, null);

        application.approve(100L);

        assertThat(application.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
        assertThat(application.getApprovedBy()).isEqualTo(100L);
    }

    @Test
    public void 신청서를_취소한다() {
        EnrollmentApplication application = new EnrollmentApplication(1L, 1L, null);

        application.cancel();

        assertThat(application.getStatus()).isEqualTo(EnrollmentStatus.CANCELLED);
    }

    @Test
    public void 이미_승인된_신청서는_다시_승인_불가() {
        EnrollmentApplication application = new EnrollmentApplication(1L, 1L, null);
        application.approve(100L);

        assertThatThrownBy(() -> application.approve(100L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("대기 중인 신청만 승인 가능합니다");
    }

    @Test
    public void 이미_취소된_신청서는_승인_불가() {
        EnrollmentApplication application = new EnrollmentApplication(1L, 1L, null);
        application.cancel();

        assertThatThrownBy(() -> application.approve(100L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("대기 중인 신청만 승인 가능합니다");
    }
}
