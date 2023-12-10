package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.image.ImageType;
import nextstep.courses.domain.course.session.*;
import nextstep.courses.service.SessionService;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    private static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private static final NsUser APPLE = new NsUser(3L, "apple", "password", "name", "apple@slipp.net");

    private Image image;
    private Payment payment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Applicants applicants;
    private Duration duration;
    private SessionState sessionState;
    private Session session;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    @BeforeEach
    public void setUp() {
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        image = new Image(1000, ImageType.GIF, Image.WIDTH_MIN, Image.HEIGHT_MIN, 1L, localDateTime);
        payment = new Payment("1", 1L, 3L, 1000L);
        localDate = LocalDate.of(2023, 12, 5);
        duration = new Duration(localDate, localDate);
        sessionState = new SessionState(SessionType.FREE, 0L, Integer.MAX_VALUE);
        applicants = new Applicants();
        applicants.addApplicant(JAVAJIGI, sessionState);
        session = new Session(1L, image, duration, sessionState, applicants,
                Session.Status.RECRUIT, 1L, localDateTime, localDateTime);
    }

    @Test
    @DisplayName("수강 신청은 수강 신청 인원에 해당 인원이 추가된다.")
    void apply_success() {
        when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));

        assertThat(session.applyCount()).isEqualTo(1);
        sessionService.applySession(APPLE, session.getId(), payment);

        assertThat(session.applyCount()).isEqualTo(2);
    }
}
