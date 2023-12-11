package nextstep.courses.domain.session;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.enums.ApplyStatus;
import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.ProgressStatus;
import nextstep.courses.domain.session.registration.SessionCapacity;
import nextstep.courses.domain.session.registration.SessionRegistration;
import nextstep.courses.domain.session.registration.Students;
import nextstep.courses.domain.session.registration.Tuition;

public class SessionTest {
	private final static Session session1 = new Session(
		1L, "자바 마스터리 30선",
		new Period(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 14)),
		ProgressStatus.IN_PROGRESS, ApplyStatus.CLOSED,
		new SessionRegistration(PaidType.PAID, new Tuition(50000), new SessionCapacity(100), new Students()), new Course("코스", 1L), null);
	private final static Session session2 = new Session(
		1L, "자바 마스터리 30선",
		new Period(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 14)),
		ProgressStatus.FINISH, ApplyStatus.APPLYING,
		new SessionRegistration(PaidType.PAID, new Tuition(50000), new SessionCapacity(100), new Students()), new Course("코스", 1L), null);

	@DisplayName("수강 신청이 종료된 강의는 신청할 수 없습니다.")
	@Test
	void validate_apply_status() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> session1.apply(JAVAJIGI, 50000))
			.withMessage("강의 신청 기간이 아닙니다.");
	}

	@DisplayName("종료된 강의는 신청할 수 없습니다.")
	@Test
	void validate_progress_status() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> session2.apply(JAVAJIGI, 50000))
			.withMessage("종료된 강의는 신청할 수 없습니다.");
	}
}
