package nextstep.payments.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageType;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.payments.entity.PaymentEntity;
import nextstep.stub.TestImageHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PaymentFactoryTest {

    @DisplayName("Payment DB 정보 및 User, Session 정보로 Payment 인스턴스 생성")
    @Test
    public void testCreate() throws IOException {
        PaymentFactory paymentFactory = new PaymentFactory();

        PaymentEntity paymentEntity = PaymentEntity.builder()
            .id(1L)
            .userId(3L)
            .sessionId(5L)
            .amount(500_000L)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();

        SessionConstraint constraint = new SessionConstraint(100, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy()
        );
        Session session = new Session("1", constraint, descriptor);

        assertDoesNotThrow(() -> paymentFactory.create(paymentEntity, session, JAVAJIGI));
    }
}
