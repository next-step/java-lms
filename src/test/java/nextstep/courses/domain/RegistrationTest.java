package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RegistrationTest {
    @Test
    void 등록() {
        Registration registration = new Registration(new PaymentService());
        Session paidSession = Session.ofPaid(LocalDateTime.now(), LocalDateTime.now().plusMonths(1),
                1_000_000L, 1, new SessionCover(300, 200, 1024, null));
        paidSession.startEnrollment();

        assertThat(registration.register(new NsUser(), paidSession, 1_000_000L).toString()).isEqualTo(new Payment(paidSession.id).toString());
    }
}