package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.infrastructure.PaymentRepository;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.sessions.domain.builder.SessionRegisterDetailsBuilder;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;
    private Payment payment;

    @BeforeEach
    void setUp() {
        session = new SessionBuilder()
                .withSessionName("TDD, CleanCode")
                .withSessionRegisterDetails(new SessionRegisterDetailsBuilder().withPrice(new Price(30000L)).build())
                .build();
        payment = new Payment("javajigi", 1L, 1L, 30000L);
    }

    @Test
    void register() {
        when(sessionRepository.findById(1L)).thenReturn(java.util.Optional.of(session));
        when(paymentRepository.findByNsUser(NsUserTest.JAVAJIGI)).thenReturn(java.util.Optional.of(payment));

        sessionService.registerSession(1L, NsUserTest.JAVAJIGI);

        assertThat(session.isContainListener(NsUserTest.JAVAJIGI)).isTrue();
    }

}
