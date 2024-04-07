package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface PaymentRepository {
    Payment findByUser(NsUser nsUser);
}
