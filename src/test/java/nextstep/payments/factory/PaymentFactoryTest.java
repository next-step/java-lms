package nextstep.payments.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.entity.PaymentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PaymentFactoryTest {

    @DisplayName("Payment DB 정보 및 User, Session 정보로 Payment 인스턴스 생성")
    @Test
    public void testCreatePayment() {
        PaymentFactory paymentFactory = new PaymentFactory();

        PaymentEntity paymentEntity = PaymentEntity.builder()
            .id(1L)
            .userId("3")
            .sessionId(5L)
            .amount(500_000L)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();

        SessionConstraint constraint = new SessionConstraint(100, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Session session = new Session("1", constraint, descriptor);

        assertDoesNotThrow(() -> paymentFactory.createPayment(paymentEntity, session, JAVAJIGI));
    }

    @DisplayName("Payment DB 정보 및 User, Session 정보로 Payments 인스턴스 생성")
    @Test
    public void testCreate() {
        PaymentFactory paymentFactory = new PaymentFactory();

        PaymentEntityUserMap paymentEntityUserMap = new PaymentEntityUserMap();

        SessionConstraint constraint = new SessionConstraint(100, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Session session = new Session("1", constraint, descriptor);

        assertDoesNotThrow(() -> paymentFactory.createPayments(session, paymentEntityUserMap));
    }
}
