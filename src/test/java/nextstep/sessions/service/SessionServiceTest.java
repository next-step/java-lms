package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.infrastructure.PaymentRepository;
import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.sessions.domain.builder.SessionRegisterDetailsBuilder;
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
    private Session session;
    private Payment payment;

    @BeforeEach
    void setUp() {
        image = Image.createImageWithCapacity(1024 * 1024);
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
