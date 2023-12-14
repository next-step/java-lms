package nextstep.payments.service;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.JdbcRegistrationRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final JdbcRegistrationRepository jdbcRegistrationRepository;

    public PaymentService(JdbcRegistrationRepository jdbcRegistrationRepository) {
        this.jdbcRegistrationRepository = jdbcRegistrationRepository;
    }

    public Payment payment(NsUser user, Session session, Long amount) {;
        jdbcRegistrationRepository.save(Registration.register(user, session, amount));
        return new Payment(session.id());
    }
}
