package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionTest {
	private Session session = new Session(
			0L,
			SessionState.RECRUITING,
			0,
			new SessionImage(1024, 300, 200, "gif"),
			LocalDateTime.of(2024, 4, 20, 12, 12, 12),
			LocalDateTime.of(2024, 5, 20, 12, 12, 12));

	private Payment payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 0L);


	@Test
	void 수강신청_성공_시_수강_인원이_증가한다() {
		int numberOfStudent = session.getNumberOfStudent();

		FreeSession freeSession = new FreeSession(session);
		freeSession.enroll(payment);

		assertThat(session.getNumberOfStudent()).isEqualTo(numberOfStudent + 1);
	}

	@Test
	void 강의_상태가_모집중이_아닐_시_수강신청_실패() {
		session.end();

		FreeSession freeSession = new FreeSession(session);
		assertThatThrownBy(() -> freeSession.enroll(payment))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("모집 중인 강의만 수강 신청 가능합니다.");
	}
}
