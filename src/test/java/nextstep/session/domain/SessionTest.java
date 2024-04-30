package nextstep.session.domain;

import nextstep.courses.domain.Course;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
	public static final Course C1 = new Course("title1", 1L);
	public static final Period P = new Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
	public static final ImageInfo imageInfo = new ImageInfo(1L, new ImageSize(1024), new ImageReSolution(300, 200), ImageType.JPG);
	public static final Session piadSession = new PaidSession(SessionTest.C1, SessionTest.imageInfo, SessionTest.P, 50000, 50);

	@Test
	void 유료강의_수강생결제금액_수강료일치_성공_실패_테스트() {
		assertThat(piadSession.isSamePaymentAndSessionPrice(50000)).isTrue();
		assertThat(piadSession.isSamePaymentAndSessionPrice(40000)).isFalse();
	}

}
