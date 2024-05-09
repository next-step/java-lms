package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {
	private Session session = new Session(
			0L,
			SessionState.RECRUITING,
			0,
			new SessionImage(1024, 300, 200, "gif"),
			LocalDateTime.of(2024, 4, 20, 12, 12, 12),
			LocalDateTime.of(2024, 5, 20, 12, 12, 12));

	private PaidSession paidSession;

	private Payment payment;

	@BeforeEach
	void setUp() {
		paidSession = new PaidSession(session, 1, 10000L);
	}

	@Test
	void 유료_강의의_최대_수강_인원은_0_이하면_예외() {
		assertThatThrownBy(() -> new PaidSession(session, 0, 50000L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("유료 강의의 최대 수강 인원은 1 이상이여야 합니다.");
	}

	@Test
	void 최대_수강인원이_넘을_경우_수강신청_실패() {
		payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 10000L);
		paidSession.enroll(payment);

		assertThatThrownBy(() -> paidSession.enroll(payment))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("수강 신청 인원을 초과했습니다.");
	}

	@Test
	void 결제_금액과_수강료_불일치_시_수강신청_실패() {
		payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 5000L);

		assertThatThrownBy(() -> paidSession.enroll(payment))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("결제 금액과 수강료가 일치하지 않습니다.");
	}
}
