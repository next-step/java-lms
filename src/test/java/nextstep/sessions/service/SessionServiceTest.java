package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.infrastructure.PaymentRepository;
import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.sessions.domain.image.Capacity;
import nextstep.sessions.domain.image.Image;
import nextstep.sessions.domain.image.ImageSize;
import nextstep.sessions.domain.image.ImageType;
import nextstep.sessions.domain.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private SessionService sessionService;

    private Image image;
    private SessionRegisterDetails sessionRegisterDetails;
    private Session session;
    private Payment payment;

    @BeforeEach
    void setUp() {
        image = new Image(1L, new Capacity(100), ImageType.PNG, new ImageSize(300, 200));
        sessionRegisterDetails = new SessionRegisterDetails(1L, 1L, new CountOfStudent(20, 40, SessionType.PAID), new Price(100000L), SessionStatus.RECRUITING, new ArrayList<>());
        session = new Session(
                1L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59),
                "tdd with java",
                image,
                sessionRegisterDetails
        );
        payment = new Payment("javajigi", 1L, 1L, 100000L);
    }

    @Test
    void register() {
        when(sessionRepository.findById(1L)).thenReturn(java.util.Optional.of(session));
        when(paymentRepository.findByNsUser(NsUserTest.JAVAJIGI)).thenReturn(java.util.Optional.of(payment));

        sessionService.registerSession(1L, NsUserTest.JAVAJIGI);

        assertThat(session.isContainListener(NsUserTest.JAVAJIGI)).isTrue();
    }

}
