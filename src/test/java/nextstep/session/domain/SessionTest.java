package nextstep.session.domain;

import nextstep.courses.domain.Course;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
	public static final Course C1 = new Course("title1", 1L);
	public static final Period P = new Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
	public static final ImageInfo imageInfo = new ImageInfo(new ImageSize(1024), new ImageReSolution(300, 200), ImageType.JPG);


	@Test
	void 무료강의_최대수강인원제한_성공_테스트() {
		Session Session = new FreeSession(C1, imageInfo, P);
		assertThat(Session.isMaximumNumberOfParticipantsLimited(100)).isTrue();
	}

	@Test
	void 수강신청_성공_테스트() {
		Session Session = new FreeSession(C1, imageInfo, P);
		assertThat(Session.isSessionRegister()).isTrue();
	}

}