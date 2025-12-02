package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {
    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Enrollment enrollment = new Enrollment(SessionStatus.RECRUITING, new FreeSessionType());

        enrollment.enroll(1L);

        assertThat(enrollment.isEnrolled(1L)).isTrue();
    }

    @Test
    public void 준비중_상태일때_수강신청_불가() {
        Enrollment enrollment = new Enrollment(SessionStatus.PREPARING, new FreeSessionType());

        assertThatThrownBy(() -> {
            enrollment.enroll(1L);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }

    @Test
    public void 유료_강의_결제금액_검증() {
        SessionType type = new PaidSessionType(10, 100_000L);
        Enrollment enrollment = new Enrollment(SessionStatus.RECRUITING, type);

        assertThatThrownBy(() -> enrollment.enroll(1L, new Payment("결제번호-1", 1L, 1L, 50_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다");
    }

    @Test
    public void 유료강의의_최대인원을_초과하면_예외() {
        long fee = 100_000L;
        SessionType type = new PaidSessionType(2, fee);
        Enrollment enrollment = new Enrollment(SessionStatus.RECRUITING, type);

        enrollment.enroll(1L, new Payment("결제번호-1", 1L, 1L, fee));
        enrollment.enroll(2L, new Payment("결제번호-2", 1L, 2L, fee));

        assertThatThrownBy(() -> {
            enrollment.enroll(3L, new Payment("결제번호-1", 1L, 3L, fee));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 수강 인원을 초과");
    }

}
