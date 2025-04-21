package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import nextstep.payments.factory.PaymentFactory;
import nextstep.payments.factory.PaymentsFactory;
import nextstep.stub.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PaymentServiceTest {

    @DisplayName("결재정보 저장 성공")
    @Test
    void testSaveSuccess() throws IOException {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository(List.of());
        TestPaymentRepository paymentRepository = new TestPaymentRepository(1L, List.of());
        TestUserRepository userRepository = new TestUserRepository(JAVAJIGI);
        TestSessionFactory sessionFactory = new TestSessionFactory();
        PaymentsFactory paymentsFactory = new TestPaymentsFactory(new PaymentFactory(), new Payments() {
            @Override
            public boolean canEnroll(Session session, Payment other) {
                return true;
            }
        });

        PaymentService paymentService = new PaymentService(
            sessionRepository,
            sessionImageRepository,
            paymentRepository,
            userRepository,
            sessionFactory,
            paymentsFactory
        ) {
            @Override
            public Payment payment(String id) {
                return new Payment("1", new Session(), NsUserTest.JAVAJIGI, 300_000L);
            }
        };

        boolean result = paymentService.save("newPaymentId", 1L);

        assertAll(
            () -> assertThat(result).isTrue(),
            () -> assertThat(paymentRepository.getSaveCalled()).isEqualTo(1)
        );
    }
}
