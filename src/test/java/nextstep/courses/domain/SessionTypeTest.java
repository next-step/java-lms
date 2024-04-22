package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTypeTest {

	@Test
	void 무료강의_최대수강인원제한_성공_테스트() {
		SessionType sessionType = new FreeSession();
		assertThat(sessionType.isMaximumNumberOfParticipantsLimited(100)).isTrue();
	}

	@Test
	void 유료강의_최대수강인원제한_성공_실패_테스트() {
		SessionType sessionType = new PaidSession(50000,50);
		assertThat(sessionType.isMaximumNumberOfParticipantsLimited(100)).isFalse();
		assertThat(sessionType.isMaximumNumberOfParticipantsLimited(50)).isTrue();
	}

	@Test
	void 유료강의_수강생결제금액_수강료일치_성공_실패_테스트() {
		SessionType sessionType = new PaidSession(50000,50);
		assertThat(sessionType.isSamePaymentAndSessionPrice(50000)).isTrue();
		assertThat(sessionType.isSamePaymentAndSessionPrice(40000)).isFalse();
	}

}