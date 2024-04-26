package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaidSessionBodyTest {

	public static final PaidSessionBody paidSessionBody = new PaidSessionBody(50000,50);

	@Test
	void 유료강의_최대수강인원제한_성공_실패_테스트() {
		Session Session = new PaidSession(SessionTest.C1, SessionTest.imageInfo, SessionTest.P, paidSessionBody);
		assertThat(Session.isMaximumNumberOfParticipantsLimited(100)).isFalse();
		assertThat(Session.isMaximumNumberOfParticipantsLimited(50)).isTrue();
	}

	@Test
	void 유료강의_수강생결제금액_수강료일치_성공_실패_테스트() {
		Session Session = new PaidSession(SessionTest.C1, SessionTest.imageInfo, SessionTest.P, paidSessionBody);
		assertThat(Session.isSamePaymentAndSessionPrice(50000)).isTrue();
		assertThat(Session.isSamePaymentAndSessionPrice(40000)).isFalse();
	}

}