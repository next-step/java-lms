package nextstep.courses.domain;

import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
	public static final Course C1 = new Course("title1", 1L);
	public static final Period P = new Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1));


	@Test
	void 무료강의_최대수강인원제한_성공_테스트() {
		Session Session = new FreeSession(C1, ImageInfoTest.imageInfo, P);
		assertThat(Session.isMaximumNumberOfParticipantsLimited(100)).isTrue();
	}

	@Test
	void 유료강의_최대수강인원제한_성공_실패_테스트() {
		Session Session = new PaidSession(C1, ImageInfoTest.imageInfo, P, 50000, 50);
		assertThat(Session.isMaximumNumberOfParticipantsLimited(100)).isFalse();
		assertThat(Session.isMaximumNumberOfParticipantsLimited(50)).isTrue();
	}

	@Test
	void 유료강의_수강생결제금액_수강료일치_성공_실패_테스트() {
		Session Session = new PaidSession(C1, ImageInfoTest.imageInfo, P, 50000, 50);
		assertThat(Session.isSamePaymentAndSessionPrice(50000)).isTrue();
		assertThat(Session.isSamePaymentAndSessionPrice(40000)).isFalse();
	}

	@Test
	void 수강신청_성공_테스트() {
		Session Session = new FreeSession(C1, ImageInfoTest.imageInfo, P);
		assertThat(Session.isSessionRegister()).isTrue();
	}

}