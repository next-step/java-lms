package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.image.Image;
import nextstep.courses.domain.course.session.image.ImageType;
import nextstep.courses.domain.course.session.image.Images;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    private static final NsUser APPLE = new NsUser(3L, "apple", "password", "name", "apple@slipp.net");
    private static final LocalDate DATE_2023_12_5 = LocalDate.of(2023, 12, 5);
    private static final LocalDate DATE_2023_12_6 = LocalDate.of(2023, 12, 6);
    private static final LocalDate DATE_2023_12_10 = LocalDate.of(2023, 12, 10);
    private static final LocalDate DATE_2023_12_12 = LocalDate.of(2023, 12, 12);

    private Images images;
    private Image image;
    private Payment payment;
    private Payment differentPayment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Applicants applicants;
    private Duration duration;
    private SessionState sessionState;
    private Session session;

    @BeforeEach
    void setUp() {
        payment = new Payment("1", 1L, 3L, 1000L);
        differentPayment = new Payment("1", 1L, 3L, 500L);
        localDate = LocalDate.of(2023, 12, 5);
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        image = new Image(1000, ImageType.GIF, Image.WIDTH_MIN, Image.HEIGHT_MIN, 1L, localDateTime);
        images = new Images(List.of(image));
        duration = new Duration(localDate, localDate);
        sessionState = new SessionState(SessionType.FREE, 0L, Integer.MAX_VALUE);
        applicants = new Applicants();
        this.applicants.addApplicant(JAVAJIGI, sessionState);
        this.applicants.addApplicant(SANJIGI, sessionState);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);
    }

    @Test
    @DisplayName("강의는 이미지가 없으면 이미지를 추가하라는 예외를 반환한다.")
    void newObject_imageNull_throwsException() {
        sessionState = new SessionState(SessionType.FREE, 0L, Integer.MAX_VALUE);
        assertThatThrownBy(
                () -> new Session(null, duration, sessionState, 1L, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 기간이 없으면 기간을 추가하라는 예외를 반환한다.")
    void newObject_durationNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(images, null, sessionState, 1L, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 강의 상태가 없으면 상태를 추가하라는 예외를 반환한다.")
    void newObject_sessionStateNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(images, duration, null, 1L, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 모집 상태에서 준비중이면 해당 인원이 추가된다.")
    void apply_recruit_ready_success() {
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.READY, 1L, localDateTime, localDateTime);

        assertThat(session.applyCount()).isEqualTo(2);

        session.apply(APPLE, payment, localDateTime);

        assertThat(session.applyCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수강 신청은 모집 상태에서 진행중이면 해당 인원이 추가된다.")
    void apply_recruit_ongoing_success() {
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);

        assertThat(session.applyCount()).isEqualTo(2);

        session.apply(APPLE, payment, localDateTime);

        assertThat(session.applyCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수강 신청은 비모집중이면 신청할 수 없다는 예외를 반환한다.")
    void apply_notRecruitStatus_throwsException() {
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.NOT_RECRUIT, SessionStatus.READY, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, payment, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 모집 중이어도 종료되었다면 신청할 수 없다는 예외를 반환한다.")
    void apply_recruitStatus_endStatus_throwsException() {
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.END, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, payment, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 수강 인원 정원을 초과하면 신청할 수 없다는 예외를 반환한다.")
    void apply_chargeSession_overQuota_throwsException() {
        applicants = new Applicants();
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 2);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);
        applicants.addApplicant(JAVAJIGI, session.getSessionState());
        applicants.addApplicant(SANJIGI, session.getSessionState());

        assertThatThrownBy(
                () -> session.apply(APPLE, payment, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 결제가 안되었다면 신청할 수 없다는 예외를 반환한다.")
    void apply_chargeSession_notPaid_throwsException() {
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 2);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, null, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 수강 금액과 지불 금액이 다르면 신청할 수 없다는 예외를 던진다.")
    void apply_chargeSession_differentAmount_throwsException() {
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 2);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, differentPayment, localDateTime)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnReady는 강의 시작날짜가 변경하려는 날짜와 같거나 늦으면 변경 할 수 없다는 예외를 던진다.")
    void changeOnReady_startDateIsBeforeOrSame_throwsException() {
        duration = new Duration(DATE_2023_12_5, DATE_2023_12_10);
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 10);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.changeOnReady(DATE_2023_12_5)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnReady(DATE_2023_12_6)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnRecruit는 강의 시작날짜가 변경하려는 날짜와 같거나 늦으면 변경 할 수 없다는 예외를 던진다.")
    void changeOnRecruit_startDateIsBeforeOrSame_throwsException() {
        duration = new Duration(DATE_2023_12_5, DATE_2023_12_10);
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 10);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.RECRUIT, SessionStatus.READY, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.changeOnRecruit(DATE_2023_12_5)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnRecruit(DATE_2023_12_6)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnRecruit는 강의 종료 날짜가 변경하려는 날짜와 같거나 늦으면 변경 할 수 없다는 예외를 던진다.")
    void changeOnEnd_EndDateIsSameOrAfter_throwsException() {
        duration = new Duration(DATE_2023_12_5, DATE_2023_12_12);
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 10);
        session = new Session(1L, images, duration, sessionState, applicants,
                RecruitStatus.NOT_RECRUIT, SessionStatus.ONGOING, 1L, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.changeOnEnd(DATE_2023_12_6)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnEnd(DATE_2023_12_12)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
