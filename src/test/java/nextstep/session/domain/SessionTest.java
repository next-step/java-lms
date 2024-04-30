package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
	public static final Course C1 = new Course("title1", 1L);
	public static final Period P = new Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
	public static final ImageInfo imageInfo = new ImageInfo(new ImageSize(1024), new ImageReSolution(300, 200), ImageType.JPG);
	public static final Session piadSession = new PaidSession(SessionTest.C1, SessionTest.imageInfo, SessionTest.P, 50000,50);

	@Test
	void 수강신청_성공_실패_테스트() {
		Session freeSession = new FreeSession(C1, imageInfo, P, 1);
		freeSession.applySession(NsUserTest.SANJIGI);
		assertThatThrownBy(() -> freeSession.applySession(NsUserTest.JAVAJIGI))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 강의_최대수강인원제한_성공_실패_테스트() {
		assertThat(piadSession.isMaximumNumberOfParticipantsLimited(100)).isFalse();
		assertThat(piadSession.isMaximumNumberOfParticipantsLimited(50)).isTrue();
	}

	@Test
	void 유료강의_수강생결제금액_수강료일치_성공_실패_테스트() {
		assertThat(piadSession.isSamePaymentAndSessionPrice(50000)).isTrue();
		assertThat(piadSession.isSamePaymentAndSessionPrice(40000)).isFalse();
	}

}
