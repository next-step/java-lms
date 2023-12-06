package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static nextstep.courses.domain.ImageShapeTest.*;
import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static org.assertj.core.api.Assertions.*;

class SessionTest {
	private static final Payment PAYMENT = new Payment(1L, 0L, 800_000L);

	private Session makeSession(SessionPaymentInfo sessionPaymentInfo) {
		return new Session(1L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentInfo);
	}

	@Test
	@DisplayName("생성_null 이거나 0_throw IllegalArgumentException")
	void 생성_validate() {
		SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(0L, 0L, 0L);
		assertThatThrownBy(() -> new Session(0L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentInfo))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
		assertThatThrownBy(() -> new Session(null, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentInfo))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
	}

	@Test
	void 무료강의_enroll() {
		SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(0L, 0L, 0L);
		Session freeSession = makeSession(sessionPaymentInfo);
		try {
			assertThat(freeSession.enroll(PAYMENT)).isEqualTo(new NsUserSession(1L, 0L));
		} catch (CannotEnrollException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void 유료강의_enroll() {
		SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(800_000L, 120L, 0L);
		Session paidSession = makeSession(sessionPaymentInfo);
		try {
			assertThat(paidSession.enroll(PAYMENT)).isEqualTo(new NsUserSession(1L, 0L));
		} catch (CannotEnrollException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void 유료_최대수강인원_초과() {
		SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(800_000L, 120L, 120L);
		Session paidSession = makeSession(sessionPaymentInfo);
		assertThatThrownBy(() -> paidSession.enroll(PAYMENT)).isInstanceOf(CannotEnrollException.class).hasMessage("수강 인원을 초과했습니다.");
	}

	@Test
	void 유료_결제금액_불일치() {
		SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(500_000L, 120L, 0L);
		Session paidSession = makeSession(sessionPaymentInfo);
		assertThatThrownBy(() -> paidSession.enroll(PAYMENT)).isInstanceOf(CannotEnrollException.class).hasMessage("결제 금액이 일치하지 않습니다.");
	}
}