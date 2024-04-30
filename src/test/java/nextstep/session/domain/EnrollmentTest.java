package nextstep.session.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {

	public final static Enrollment E1 = new Enrollment(1);
	public final static Enrollment E2 = new Enrollment(50);

	@Test
	void 수강신청_성공_실패_테스트() {
		E1.applySession(NsUserTest.SANJIGI);
		assertThatThrownBy(() -> E1.applySession(NsUserTest.JAVAJIGI))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 강의_최대수강인원제한_성공_실패_테스트() {
		assertThat(E2.isMaximumNumberOfParticipantsLimited(100)).isFalse();
		assertThat(E2.isMaximumNumberOfParticipantsLimited(50)).isTrue();
	}

}