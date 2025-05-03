package nextstep.payments.integration;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.service.CourseService;
import nextstep.courses.service.SessionService;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class PaymentServiceIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentRepository paymentRepository;

    // enrollTest는 payment() 함수가 작동을 하지 않는 함수이기 때문에 통합 테스트 하지 않음

    @Transactional
    @Test
    void testApprove() throws IOException {
        long savedCourseId = courseService.createCourse("Test Course", 1L);

        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Long savedSessionId = sessionService.createSession(savedCourseId, new Session(constraint, descriptor));
        Session session = sessionService.getSession(savedSessionId);

        userService.saveUser(new NsUser("100", "password", "test", "javajigi@slipp.net", "우아한테크코스"));
        NsUser nsUser = userService.getUser("100");

        Payment payment = new Payment(session, nsUser, 200_000L);
        long savedPaymentId = paymentService.createPayment(payment);

        userService.saveUser(new NsUser("101", "password", "test", "javajigi@slipp.net", "강사"));
        paymentService.approve(savedPaymentId, "101");

        assertThat(paymentRepository.findById(savedPaymentId).getStatus()).isEqualTo("승인 완료");
    }

    @Transactional
    @Test
    void testCancel() throws IOException {
        long savedCourseId = courseService.createCourse("Test Course", 1L);

        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Long savedSessionId = sessionService.createSession(savedCourseId, new Session(constraint, descriptor));
        Session session = sessionService.getSession(savedSessionId);

        userService.saveUser(new NsUser("200", "password", "test", "javajigi@slipp.net", "비 선발 인원"));
        NsUser nsUser = userService.getUser("200");

        Payment payment = new Payment(session, nsUser, 200_000L);
        long savedPaymentId = paymentService.createPayment(payment);

        userService.saveUser(new NsUser("201", "password", "test", "javajigi@slipp.net", "강사"));
        paymentService.cancel(savedPaymentId, "201");

        assertThat(paymentRepository.findById(savedPaymentId).getStatus()).isEqualTo("승인 취소");
    }

    @Transactional
    @Test
    void testSavePayment() throws IOException {
        long savedCourseId = courseService.createCourse("Test Course", 1L);

        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Long savedSessionId = sessionService.createSession(savedCourseId, new Session(constraint, descriptor));
        Session session = sessionService.getSession(savedSessionId);

        userService.saveUser(new NsUser("300", "password", "test", "javajigi@slipp.net", "비 선발 인원"));
        NsUser nsUser = userService.getUser("300");

        assertDoesNotThrow(() -> paymentService.createPayment(new Payment(session, nsUser, 200_000L)));
    }
}
