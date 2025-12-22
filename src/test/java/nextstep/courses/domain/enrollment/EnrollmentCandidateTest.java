package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentCandidateTest {
    @Test
    public void 수강후보자를_생성한다() {
        Payment payment = new Payment("결제번호-1", 1L, 1L, 50_000L);

        EnrollmentCandidate candidate = new EnrollmentCandidate(1L, 1L);

        assertThat(candidate.getSessionId()).isEqualTo(1L);
        assertThat(candidate.getNsUserId()).isEqualTo(1L);
        assertThat(candidate.getStatus()).isEqualTo(EnrollmentStatus.PENDING);
    }

    @Test
    public void 수강후보자를_승인한다() {
        EnrollmentCandidate candidate = new EnrollmentCandidate(1L, 1L);

        candidate.approve(100L);

        assertThat(candidate.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    public void 수강후보자를_취소한다() {
        EnrollmentCandidate candidate = new EnrollmentCandidate(1L, 1L);

        candidate.cancel();

        assertThat(candidate.getStatus()).isEqualTo(EnrollmentStatus.CANCELLED);
    }

    @Test
    public void 이미_승인된_수강후보자는_다시_승인_불가() {
        EnrollmentCandidate candidate = new EnrollmentCandidate(1L, 1L);
        candidate.approve(100L);

        assertThatThrownBy(() -> candidate.approve(100L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("대기 중인 신청만 승인 가능합니다");
    }

    @Test
    public void 이미_취소된_수강후보자는_승인_불가() {
        EnrollmentCandidate candidate = new EnrollmentCandidate(1L, 1L);
        candidate.cancel();

        assertThatThrownBy(() -> candidate.approve(100L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("대기 중인 신청만 승인 가능합니다");
    }
}
