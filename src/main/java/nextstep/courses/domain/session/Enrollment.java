package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public interface Enrollment {
	void enroll(Payment payment);
}
