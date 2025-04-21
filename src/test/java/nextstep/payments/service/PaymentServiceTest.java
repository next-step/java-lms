package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import nextstep.payments.entity.PaymentEntity;
import nextstep.payments.factory.PaymentEntityFactory;
import nextstep.stub.factory.TestPaymentFactory;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestPaymentRepository;
import nextstep.stub.repository.TestSessionImageRepository;
import nextstep.stub.repository.TestSessionRepository;
import nextstep.stub.repository.TestUserRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.time.LocalDateTime;
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
        TestUserRepository userRepository = new TestUserRepository(1L);
        userRepository.addUser("1", JAVAJIGI);
        TestSessionFactory sessionFactory = new TestSessionFactory();
        TestPaymentFactory paymentFactory = new TestPaymentFactory(new Payments() {
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
            paymentFactory,
            new PaymentEntityFactory()
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

    @DisplayName("결재정보 승인 테스트")
    @ParameterizedTest(name = "{index} => approverRole={0}, applicantRole={1}, expectedResult={2}")
    @CsvSource({
        "강사, 우아한테크코스, true",
        "강사, 비 선발 인원, false"
    })
    void testApprove(String approverRole, String applicantRole, boolean expectedResult) throws IOException {
        NsUser approver = new NsUser("1", "password", "강사1", "test@naver.com", approverRole);
        NsUser applicant = new NsUser("2", "password", "참여자1", "test@naver.com", applicantRole);
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository(List.of());

        PaymentEntity paymentEntity = PaymentEntity.builder()
            .id(1L)
            .userId(2L)
            .sessionId(5L)
            .amount(500_000L)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();
        TestPaymentRepository paymentRepository = new TestPaymentRepository(1L, paymentEntity);

        TestUserRepository userRepository = new TestUserRepository(1L);
        userRepository.addUser("1", approver);
        userRepository.addUser("2", applicant);
        TestSessionFactory sessionFactory = new TestSessionFactory();

        Payment payment = new Payment("10", new Session(), applicant, 300_000L);
        TestPaymentFactory paymentFactory = new TestPaymentFactory(payment);

        PaymentService paymentService = new PaymentService(
            sessionRepository,
            sessionImageRepository,
            paymentRepository,
            userRepository,
            sessionFactory,
            paymentFactory,
            new PaymentEntityFactory()
        ) {
            @Override
            public Payment payment(String id) {
                return new Payment("1", new Session(), NsUserTest.JAVAJIGI, 300_000L);
            }
        };

        assertThat(paymentService.approve(10L, "1")).isEqualTo(expectedResult);
    }

    @DisplayName("결재정보 취소 테스트")
    @ParameterizedTest(name = "{index} => approverRole={0}, applicantRole={1}, expectedResult={2}")
    @CsvSource({
        "강사, 우아한테크코스, false",
        "강사, 비 선발 인원, true"
    })
    void testCancel(String approverRole, String applicantRole, boolean expectedResult) throws IOException {
        NsUser approver = new NsUser("1", "password", "강사1", "test@naver.com", approverRole);
        NsUser applicant = new NsUser("2", "password", "참여자1", "test@naver.com", applicantRole);
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository(List.of());

        PaymentEntity paymentEntity = PaymentEntity.builder()
            .id(1L)
            .userId(2L)
            .sessionId(5L)
            .amount(500_000L)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();
        TestPaymentRepository paymentRepository = new TestPaymentRepository(1L, paymentEntity);

        TestUserRepository userRepository = new TestUserRepository(1L);
        userRepository.addUser("1", approver);
        userRepository.addUser("2", applicant);
        TestSessionFactory sessionFactory = new TestSessionFactory();

        Payment payment = new Payment("10", new Session(), applicant, 300_000L);
        TestPaymentFactory paymentFactory = new TestPaymentFactory(payment);

//        TestPaymentsFactory paymentsFactory = new TestPaymentsFactory(new PaymentFactory(), new Payments() {
//            @Override
//            public boolean canEnroll(Session session, Payment other) {
//                return true;
//            }
//        });

        PaymentService paymentService = new PaymentService(
            sessionRepository,
            sessionImageRepository,
            paymentRepository,
            userRepository,
            sessionFactory,
            paymentFactory,
            new PaymentEntityFactory()
        ) {
            @Override
            public Payment payment(String id) {
                return new Payment("1", new Session(), NsUserTest.JAVAJIGI, 300_000L);
            }
        };

        assertThat(paymentService.cancel(10L, "1")).isEqualTo(expectedResult);
    }
}
