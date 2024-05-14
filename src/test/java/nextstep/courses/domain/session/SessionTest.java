package nextstep.courses.domain.session;

import nextstep.courses.builder.PaidSessionBuilder;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
	private final Long fee = 10000L;

	private Session session = new PaidSessionBuilder()
			.fee(fee)
			.build();

	private Payment payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), fee);

	@Test
	void 수강신청_성공_시_수강_인원이_증가한다() {
		int numberOfStudent = session.getNumberOfStudent();

		session.enroll(payment);

		assertThat(session.getNumberOfStudent()).isEqualTo(numberOfStudent + 1);
	}

	@Test
	void 강의_상태가_모집중이_아닐_시_수강신청_실패() {
		session.end();

		assertThatThrownBy(() -> session.enroll(payment))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("모집 중인 강의만 수강 신청 가능합니다.");
	}

	@Test
	void 유료_강의의_최대_수강_인원은_0_이하면_예외() {
		assertThatThrownBy(() -> new PaidSessionBuilder().maxNumberOfStudent(0).build())
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("유료 강의의 최대 수강 인원은 1 이상이여야 합니다.");
	}

	@Test
	void 최대_수강인원이_넘을_경우_수강신청_실패() {
		Session sessionWithMaxNumberOfStudent = new PaidSessionBuilder().maxNumberOfStudent(1).build();
		sessionWithMaxNumberOfStudent.enroll(payment);

		assertThatThrownBy(() -> sessionWithMaxNumberOfStudent.enroll(payment))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("수강 신청 인원을 초과했습니다.");
	}

	@Test
	void 결제_금액과_수강료_불일치_시_수강신청_실패() {
		Payment paymentWithDifferAmount = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 5000L);

		assertThatThrownBy(() -> session.enroll(paymentWithDifferAmount))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("결제 금액과 수강료가 일치하지 않습니다.");
	}
}
