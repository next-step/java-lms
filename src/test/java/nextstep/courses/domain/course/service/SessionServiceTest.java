package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.image.ImageType;
import nextstep.courses.domain.course.session.*;
import nextstep.courses.service.SessionService;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
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
    private static final LocalDate DATE_2023_12_5 = LocalDate.of(2023, 12, 5);
    private static final LocalDate DATE_2023_12_6 = LocalDate.of(2023, 12, 6);
    private static final LocalDate DATE_2023_12_10 = LocalDate.of(2023, 12, 10);
    private static final LocalDate DATE_2023_12_12 = LocalDate.of(2023, 12, 12);

    private Image image;
    private Payment payment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Applicants applicants;
    private Duration duration;
    private SessionState sessionState;
    private SessionStatus sessionStatus;
    private Session session;
    private Session savedSession;

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
        sessionStatus = SessionStatus.RECRUIT;
        applicants = new Applicants();
        applicants.addApplicant(JAVAJIGI, sessionState);
        session = new Session(1L, image, duration, sessionState, applicants,
                sessionStatus, 1L, localDateTime, localDateTime);
    }

    @Test
    @DisplayName("주어진 강의 정보로 강의를 생성한다.")
    void create_success() {
        Session newSession = new Session(image, duration, sessionState, 1L, localDateTime);
        Session savedSession = new Session(1L, image, duration, sessionState, new Applicants(),
                SessionStatus.READY, 1L, localDateTime, localDateTime);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(savedSession));

        sessionService.create(1L, newSession, localDateTime);
        Session findSession = sessionRepository.findById(1L).orElseThrow(NotFoundException::new);

        assertThat(findSession.getId()).isEqualTo(1L);
        assertThat(findSession.getSessionStatus()).isEqualTo(SessionStatus.READY);
        assertThat(findSession.getApplicants()).hasSize(0);
    }

    @Test
    @DisplayName("수강 신청은 수강 신청 인원에 해당 인원이 추가된다.")
    void apply_success() {
        when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));

        assertThat(session.applyCount()).isEqualTo(1);
        sessionService.applySession(APPLE, session.getId(), payment, localDateTime);

        assertThat(session.applyCount()).isEqualTo(2);
        assertThat(session.getApplicants()).contains(APPLE);
    }

    @Test
    @DisplayName("강의 시작 날짜 전이라면 주어진 식별자에 해당하는 강의를 준비 상태로 변경한다.")
    void changeOnReady_success() {
        duration = new Duration(DATE_2023_12_6, DATE_2023_12_12);
        savedSession = new Session(1L, image, duration, sessionState, new Applicants(),
                SessionStatus.RECRUIT, 1L, localDateTime, localDateTime);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(savedSession));

        sessionService.changeOnReady(1L, DATE_2023_12_5);

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionStatus.READY);
    }

    @Test
    @DisplayName("강의 시작 날짜 전이라면 주어진 식별자에 해당하는 강의를 모집중 상태로 변경한다.")
    void changeOnRecruit_success() {
        duration = new Duration(DATE_2023_12_6, DATE_2023_12_12);
        savedSession = new Session(1L, image, duration, sessionState, new Applicants(),
                SessionStatus.READY, 1L, localDateTime, localDateTime);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(savedSession));

        sessionService.changeOnRecruit(1L, DATE_2023_12_5);

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionStatus.RECRUIT);
    }

    @Test
    @DisplayName("강의 종료날짜 이후라면 주어진 식별자에 해당하는 강의를 종료 상태로 변경한다.")
    void changeOnEnd_success() {
        duration = new Duration(DATE_2023_12_5, DATE_2023_12_10);
        savedSession = new Session(1L, image, duration, sessionState, new Applicants(),
                SessionStatus.READY, 1L, localDateTime, localDateTime);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(savedSession));

        sessionService.changeOnEnd(1L, DATE_2023_12_12);

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionStatus.END);
    }
}
