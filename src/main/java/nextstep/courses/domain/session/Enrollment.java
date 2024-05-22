package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface Enrollment {
	void enroll(NsUser nsUser, Payment payment);
}
