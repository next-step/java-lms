package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import nextstep.courses.domain.enums.SessionType;

class SessionTypeTest {

	public static final String PAY_SESSION_STR = "P";
	public static final String FREE_SESSION_STR = "F";

	@Test
	@DisplayName("문자열 매개변수로 type")
	void findByTypeStr() {
		Optional<SessionType> sessionType = SessionType.findByTypeStr("P");
		assertThat(sessionType.get()).isEqualTo(SessionType.PAY);
	}

	@Test
	@DisplayName("isPay 호출 시 문자열 매개변수가 'P'인 경우 true 반환")
	void isPay() {
		assertThat(SessionType.isPaySession(PAY_SESSION_STR)).isTrue();
		assertThat(SessionType.isPaySession(FREE_SESSION_STR)).isFalse();
	}

}
