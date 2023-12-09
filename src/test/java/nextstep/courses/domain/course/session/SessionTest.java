package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private Image image;
    private Payment payment;
    private Payment differentPayment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private int quota;
    private Applicants applicants;
    private Duration duration;
    private Session session;

    @BeforeEach
    void setUp() {
        image = new Image(1000, "jpeg", Image.WIDTH_MIN, Image.HEIGHT_MIN);
        payment = new Payment("1", 1L, 3L, 1000L);
        differentPayment = new Payment("1", 1L, 3L, 500L);
        localDate = LocalDate.of(2023, 12, 5);
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        quota = 10;
        applicants = new Applicants(quota);
        this.applicants.addApplicant(JAVAJIGI, Session.Type.CHARGE);
        this.applicants.addApplicant(SANJIGI, Session.Type.CHARGE);
        duration = new Duration(localDate, localDate);
        session = new Session(1L, image, duration, Session.Type.FREE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);
    }

    @Test
    @DisplayName("강의는 이미지가 없으면 이미지를 추가하라는 예외를 반환한다.")
    void newObject_imageNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(null, duration, Session.Type.FREE, 1000L, 10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 기간이 없으면 기간을 추가하라는 예외를 반환한다.")
    void newObject_durationNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(image, null, Session.Type.FREE, 1000L, 10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 수강 신청 인원에 해당 인원이 추가된다.")
    void apply_success() {
        Session session = new Session(1L, image, duration, Session.Type.FREE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThat(session.applyCount()).isEqualTo(2);

        session.apply(APPLE, payment);

        assertThat(session.applyCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수강 신청은 모집 중이 아니면 신청할 수 없다는 예외를 반환한다.")
    void apply_notRecruitStatus_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.FREE, 1000L,
                applicants, Session.Status.READY, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, payment)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 수강 인원 정원을 초과하면 신청할 수 없다는 예외를 반환한다.")
    void apply_chargeSession_overQuota_throwsException() {
        applicants = new Applicants(2);
        applicants.addApplicant(JAVAJIGI, Session.Type.CHARGE);
        applicants.addApplicant(SANJIGI, Session.Type.CHARGE);

        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, payment)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 결제가 안되었다면 신청할 수 없다는 예외를 반환한다.")
    void apply_chargeSession_notPaid_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 수강 금액과 지불 금액이 다르면 신청할 수 없다는 예외를 던진다.")
    void apply_chargeSession_differentAmount_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, differentPayment)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnReady는 강의 시작날짜가 변경하려는 날짜와 같거나 늦으면 변경 할 수 없다는 예외를 던진다.")
    void changeOnReady_startDateIsBeforeOrSame_throwsException() {
        Duration duration = new Duration(DATE_2023_12_5, DATE_2023_12_10);
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

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
        Duration duration = new Duration(DATE_2023_12_5, DATE_2023_12_10);
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                applicants, Session.Status.READY, localDateTime, localDateTime);

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
        Duration duration = new Duration(DATE_2023_12_5, DATE_2023_12_12);
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.changeOnEnd(DATE_2023_12_6)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnEnd(DATE_2023_12_12)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
