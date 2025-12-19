package nextstep.payments.service.repository;

import java.util.Optional;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository {
    Optional<Payment> findById(String id);
}
