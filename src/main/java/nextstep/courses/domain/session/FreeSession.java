package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class FreeSession implements Enrollment {
	Session session;

	public FreeSession(Session session) {
		this.session = session;
	}

	@Override
	public void enroll(Payment payment) {
		session.enroll(payment);
	}
}
