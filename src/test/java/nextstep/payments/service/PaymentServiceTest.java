package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentStatus;
import nextstep.payments.domain.Payments;
import nextstep.payments.entity.PaymentEntity;
import nextstep.stub.factory.TestPaymentFactory;
import nextstep.stub.repository.TestPaymentRepository;
import nextstep.stub.service.TestSessionService;
import nextstep.stub.service.TestUserService;
import nextstep.users.domain.NsUser;
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

    @DisplayName("결재정보 등록 성공")
    @Test
    void testSaveSuccess() throws IOException {
        TestPaymentRepository paymentRepository = new TestPaymentRepository(1L);
        TestPaymentFactory paymentFactory = new TestPaymentFactory(new Payments() {
            @Override
            public boolean canEnroll(Session session, Payment other) {
                return true;
            }
        });
        TestSessionService sessionService = new TestSessionService() {
            @Override
            public Session getSession(long sessionId) {
                return new Session();
            }
        };
        TestUserService userService = new TestUserService() {
            @Override
            public List<NsUser> getUsers(List<String> userIds) {
                return List.of();
            }
        };

        PaymentService paymentService = new PaymentService(
            paymentRepository,
            paymentFactory,
            sessionService,
            userService
        ) {
            @Override
            public Payment payment(String id) {
                return new Payment("1", new Session(), JAVAJIGI, 300_000L);
            }
        };

        boolean result = paymentService.enroll("newPaymentId", 1L);

        assertAll(
            () -> assertThat(result).isTrue(),
            () -> assertThat(paymentRepository.getSaveCalled()).isEqualTo(1)
        );
    }

    @DisplayName("결재정보 승인 테스트")
    @ParameterizedTest(name = "{index} => expectedResult={0}")
    @CsvSource({"true", "false"})
    void testApprove(boolean expectedResult) {
        TestPaymentRepository paymentRepository = new TestPaymentRepository(1L, createPaymentEntity(1L, "2", 5L));
        TestPaymentFactory paymentFactory = new TestPaymentFactory();
        TestSessionService sessionService = new TestSessionService();
        TestUserService userService = new TestUserService() {
            @Override
            public boolean canApprove(String approverId, String applicantId) {
                return expectedResult;
            }
        };

        PaymentService paymentService = new PaymentService(
            paymentRepository,
            paymentFactory,
            sessionService,
            userService
        );

        assertThat(paymentService.approve(10L, "1")).isEqualTo(expectedResult);
    }

    @DisplayName("결재정보 취소 테스트")
    @ParameterizedTest(name = "{index} => expectedResult={0}")
    @CsvSource({"true", "false"})
    void testCancel(boolean expectedResult) {
        TestPaymentRepository paymentRepository = new TestPaymentRepository(
            1L,
            createPaymentEntity(1L, "2", 5L)
        );
        TestPaymentFactory paymentFactory = new TestPaymentFactory(new Payment());
        TestSessionService sessionService = new TestSessionService();
        TestUserService userService = new TestUserService() {
            @Override
            public boolean canCancel(String approverId, String applicantId) {
                return expectedResult;
            }
        };

        PaymentService paymentService = new PaymentService(
            paymentRepository,
            paymentFactory,
            sessionService,
            userService
        );

        assertThat(paymentService.cancel(10L, "1")).isEqualTo(expectedResult);
    }

    @DisplayName("결재 정보 생성")
    @Test
    void testCreatePayment() {
        TestPaymentRepository paymentRepository =
            new TestPaymentRepository(1L, createPaymentEntity(1L, "2", 5L));
        TestPaymentFactory paymentFactory = new TestPaymentFactory();
        TestSessionService sessionService = new TestSessionService();
        TestUserService userService = new TestUserService();

        PaymentService paymentService = new PaymentService(
            paymentRepository,
            paymentFactory,
            sessionService,
            userService
        );

        paymentService.createPayment(new Payment());

        assertThat(paymentRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("결재 정보 상태 업데이트")
    @Test
    void testUpdatePaymentStatus() {
        TestPaymentRepository paymentRepository =
            new TestPaymentRepository(1L, createPaymentEntity(1L, "2", 5L));
        TestPaymentFactory paymentFactory = new TestPaymentFactory();
        TestSessionService sessionService = new TestSessionService();
        TestUserService userService = new TestUserService();

        PaymentService paymentService = new PaymentService(
            paymentRepository,
            paymentFactory,
            sessionService,
            userService
        );

        paymentService.updatePaymentStatus(1L, PaymentStatus.APPROVED);

        assertThat(paymentRepository.getUpdateCalled()).isEqualTo(1);
    }

    private PaymentEntity createPaymentEntity(Long id, String userId, Long sessionId) {
        return PaymentEntity.builder()
            .id(id)
            .userId(userId)
            .sessionId(sessionId)
            .amount(300_000L)
            .status("대기중")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();
    }
}
