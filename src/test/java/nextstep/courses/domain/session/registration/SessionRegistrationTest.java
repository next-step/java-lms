package nextstep.courses.domain.session.registration;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.enums.ApprovalStatus;
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
			new Students(
				List.of(new Registration(JAVAJIGI, null, ApprovalStatus.APPROVAL))
			)
		);
		paidSession = new SessionRegistration(
			PaidType.PAID,
			new Tuition(50000),
			new SessionCapacity(1),
			new Students(
				List.of(
					new Registration(SANJIGI, null, ApprovalStatus.APPROVAL),
					new Registration(JAVAJIGI, null, ApprovalStatus.APPROVAL)
				)
			)
		);
	}

	@DisplayName("강의가 유료일 때 강의 최대 수용인원 검사를 한다.")
	@Test
	void validate_amount_by_paidSession() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> paidSession.validateCapacity())
			.withMessage("최대 수강 인원을 초과했습니다.");
	}
}
