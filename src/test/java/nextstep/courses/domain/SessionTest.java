package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static org.assertj.core.api.Assertions.*;

class SessionTest {
	private static final Payment PAYMENT = new Payment(1L, 0L, 800_000L);

	private Session makeSession(SessionPaymentCondition sessionPaymentCondition, Long userNumber) {
		return new Session(1L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentCondition, userNumber);
	}

	@Test
	@DisplayName("생성_null 이거나 0_throw IllegalArgumentException")
	void 생성_validate() {
		SessionPaymentCondition sessionPaymentCondition = new SessionPaymentCondition(0L, 0L);
		assertThatThrownBy(() -> new Session(0L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentCondition, 0L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
		assertThatThrownBy(() -> new Session(null, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentCondition, 0L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
	}

	@Test
	void 무료강의_enroll() {
		SessionPaymentCondition sessionPaymentCondition = new SessionPaymentCondition(0L, 0L);
		Session freeSession = makeSession(sessionPaymentCondition, 0L);
		try {
			assertThat(freeSession.enroll(PAYMENT)).isEqualTo(new NsUserSession(1L, 0L));
		} catch (CannotEnrollException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void 유료강의_enroll() {
		SessionPaymentCondition sessionPaymentCondition = new SessionPaymentCondition(800_000L, 120L);
		Session paidSession = makeSession(sessionPaymentCondition, 0L);
		try {
			assertThat(paidSession.enroll(PAYMENT)).isEqualTo(new NsUserSession(1L, 0L));
		} catch (CannotEnrollException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void 유료_최대수강인원_초과() {
		SessionPaymentCondition sessionPaymentCondition = new SessionPaymentCondition(800_000L, 120L);
		Session paidSession = makeSession(sessionPaymentCondition, 120L);
		assertThatThrownBy(() -> paidSession.enroll(PAYMENT)).isInstanceOf(CannotEnrollException.class).hasMessage("수강 인원을 초과했습니다.");
	}

	@Test
	void 유료_결제금액_불일치() {
		SessionPaymentCondition sessionPaymentCondition = new SessionPaymentCondition(500_000L, 120L);
		Session paidSession = makeSession(sessionPaymentCondition, 0L);
		assertThatThrownBy(() -> paidSession.enroll(PAYMENT)).isInstanceOf(CannotEnrollException.class).hasMessage("결제 금액이 일치하지 않습니다.");
	}
}
