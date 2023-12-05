package nextstep.courses.domain.session.registration;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.enums.PaidType;

public class SessionRegistrationTest {

	SessionRegistration freeSession;
	SessionRegistration paidSession;

	@BeforeEach
	void setUp() {
		freeSession = new SessionRegistration(
			PaidType.FREE,
			new Tuition(0),
			new SessionCapacity(0),
			new Students()
		);
		paidSession = new SessionRegistration(
			PaidType.PAID,
			new Tuition(50000),
			new SessionCapacity(1),
			new Students()
		);
	}

	@DisplayName("강의 최대 수용인원이 유료일 때만 최대 수용인원 검사를 한다.")
	@Test
	void validate_amount() {
		freeSession.register(null, JAVAJIGI);
		freeSession.register(null, SANJIGI);

		paidSession.register(null, JAVAJIGI);
		assertThatIllegalArgumentException()
			.isThrownBy(() -> paidSession.validate(50000))
			.withMessage("최대 수강 인원을 초과했습니다.");
	}
}
