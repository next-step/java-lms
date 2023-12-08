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

    private Image image;
    private Payment payment;
    private Payment differentPayment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Applicants applicants = new Applicants();
    private Duration duration;
    private Session session;

    @BeforeEach
    void setUp() {
        image = new Image(1000, "jpeg", Image.WIDTH_MIN, Image.HEIGHT_MIN);
        payment = new Payment("1", 1L, 3L, 1000L);
        differentPayment = new Payment("1", 1L, 3L, 500L);
        localDate = LocalDate.of(2023, 12, 5);
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        this.applicants.add(JAVAJIGI);
        this.applicants.add(SANJIGI);
        duration = new Duration(localDate, localDate);
        session = new Session(1L, image, duration, Session.Type.FREE, 1000L,
                10, applicants, Session.Status.RECRUIT, localDateTime, localDateTime);
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
                10, applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThat(session.applyCount()).isEqualTo(2);

        session.apply(APPLE, payment);

        assertThat(session.applyCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수강 신청은 모집 중이 아니면 신청할 수 없다는 예외를 반환한다.")
    void apply_notRecruitStatus_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.FREE, 1000L,
                10, applicants, Session.Status.READY, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, payment)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 수강 인원 정원을 초과하면 신청할 수 없다는 예외를 반환한다.")
    void apply_chargeSession_overQuota_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                2, applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, payment)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 결제가 안되었다면 신청할 수 없다는 예외를 반환한다.")
    void apply_chargeSession_notPaid_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                10, applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 수강 금액과 지불 금액이 다르면 신청할 수 없다는 예외를 던진다.")
    void apply_chargeSession_differentAmount_throwsException() {
        Session session = new Session(1L, image, duration, Session.Type.CHARGE, 1000L,
                10, applicants, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.apply(APPLE, differentPayment)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
