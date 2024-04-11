package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository {
    Optional<Payment> findByUser(NsUser nsUser);
}
