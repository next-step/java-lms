package nextstep.courses.domain;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.Status;

public class SessionTest {

	@DisplayName("강의 신청 기간이 아니면 수강신청할 수 없습니다.")
	@Test
	void validate_amount() {
		Session session = new Session(
			LocalDateTime.now(), LocalDateTime.now(), 1L, "임시 강의",null,null, Status.READY,
			new SessionRegistration(PaidType.PAID, new Tuition(50000), new SessionCapacity(1), new Students())
		);
		assertThatIllegalArgumentException()
			.isThrownBy(() -> session.apply(JAVAJIGI, 50000))
			.withMessage("강의 신청 기간이 아닙니다.");
	}
}
