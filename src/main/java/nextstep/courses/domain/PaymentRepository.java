package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository {
    Payment findByUser(NsUser nsUser);
}
