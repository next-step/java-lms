package nextstep.courses.domain.session;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.Status;
import nextstep.courses.domain.session.registration.SessionCapacity;
import nextstep.courses.domain.session.registration.SessionRegistration;
import nextstep.courses.domain.session.registration.Students;
import nextstep.courses.domain.session.registration.Tuition;

public class SessionTest {

	@DisplayName("강의 신청 기간이 아니면 수강신청할 수 없습니다.")
	@Test
	void validate_amount() {
		Session session = new Session(
			LocalDateTime.now(), LocalDateTime.now(), 1L, "임시 강의",null,null, Status.READY,
			new SessionRegistration(PaidType.PAID, new Tuition(50000), new SessionCapacity(1), new Students())
			,null
		);
		assertThatIllegalArgumentException()
			.isThrownBy(() -> session.apply(JAVAJIGI, 50000))
			.withMessage("강의 신청 기간이 아닙니다.");
	}
}
