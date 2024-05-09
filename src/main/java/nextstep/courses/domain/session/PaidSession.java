package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class PaidSession implements Enrollment {
	private Session session;

	private int maxNumberOfStudent;

	private Long fee;

	public PaidSession(Session session, int maxNumberOfStudent, Long fee) {
		if (maxNumberOfStudent <= 0) {
			throw new IllegalArgumentException("유료 강의의 최대 수강 인원은 1 이상이여야 합니다.");
		}

		this.session = session;
		this.maxNumberOfStudent = maxNumberOfStudent;
		this.fee = fee;
	}

	@Override
	public void enroll(Payment payment) {
		if(session.getNumberOfStudent() >= maxNumberOfStudent) {
			throw new IllegalArgumentException("수강 신청 인원을 초과했습니다.");
		}

		if(payment.isNotEqualsAmount(fee)) {
			throw new IllegalArgumentException("결제 금액과 수강료가 일치하지 않습니다.");
		}

		session.enroll(payment);
	}
}
