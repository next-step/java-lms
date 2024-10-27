package nextstep.session;

import nextstep.payments.domain.Payment;
import nextstep.session.image.Image;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @DisplayName("무료 강의를 생성한다.")
    @Test
    void createFreeSessionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createFree(1L, "테스트강의", image, startDate, endDate);

        assertThat(session)
                .extracting("title", "image", "paymentType", "startDate", "endDate")
                .contains("테스트강의", PaymentType.FREE, image, startDate, endDate);
    }

    @DisplayName("유료 강의를 생성한다.")
    @Test
    void createPaidSessionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);

        assertThat(session)
                .extracting("title", "image", "paymentType", "startDate", "endDate", "subscribeMax", "price")
                .contains("테스트강의", PaymentType.PAID, image, 100, 800000, startDate, endDate);
    }

    @DisplayName("강의를 생성할 시 시작일보다 종료일이 빠르면 예외가 발생한다.")
    @Test
    void createSessionNotExistStartDateThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-05-05");
        Date endDate = simpleDateFormat.parse("2023-04-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        assertThatThrownBy(() -> Session.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료일이 시작일보다 빠릅니다.");
    }

    @DisplayName("강의를 모집중으로 변경한다.")
    @Test
    void SessionWaitTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createFree(1L, "테스트강의", image, startDate, endDate);
        session.waitSession();
        assertThat(session.getSubscribeStatus()).isEqualTo(SubscribeStatus.WAIT);
    }

    @DisplayName("강의를 종료한다.")
    @Test
    void SessionClosedTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createFree(1L, "테스트강의", image, startDate, endDate);
        session.closedSession();
        assertThat(session.getSubscribeStatus()).isEqualTo(SubscribeStatus.CLOSED);
    }

    @DisplayName("무료 강의를 신청할 시 수강인원이 1 증가한다.")
    @Test
    void subscribeSessionAddSubscribeCountTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createFree(1L, "테스트강의", image, startDate, endDate);
        session.waitSession();

        NsUser user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        session.subsribe(user);
        assertThat(session.getSubscribeCount()).isEqualTo(1);
    }

    @DisplayName("강의를 신청할 시 모집중이 아니면 예외가 발생한다")
    @Test
    void subscribeSessionNotWaitThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);

        Payment payment = new Payment(1L, 1L, 1L, 700000);

        NsUser user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        assertThatThrownBy(() -> session.subsribe(user, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의가 모집중이 아닙니다.");
    }

    @DisplayName("유료 강의를 신청할 시 수강생이 결제한 금액과 수강료가 일치하지 않으면 예외가 발생한다.")
    @Test
    void subscribeSessionNotCorrectPaymentThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);

        NsUser user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        Payment payment = new Payment(1L, 1L, 1L, 700000);

        session.waitSession();

        assertThatThrownBy(() -> session.subsribe(user, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @DisplayName("유료 강의를 신청할 시 결제내역이 없으면 예외가 발생한다.")
    @Test
    void subscribeSessionWithoutPaymentThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);
        session.waitSession();

        NsUser user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        assertThatThrownBy(() -> session.subsribe(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료강의는 결제내역이 필수입니다.");
    }

    @DisplayName("강의가 이미 만석이면 예외를 발생한다.")
    @Test
    void subscribeSessionAlreadyMaxThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Session session = Session.createPaid(1L, "테스트강의", image, 1, 800000, startDate, endDate);

        NsUser user1 = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsUser user2 = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        Payment payment = new Payment(1L, 1L, 1L, 800000);

        session.waitSession();
        session.subsribe(user1, payment);

        assertThatThrownBy(() -> session.subsribe(user2, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의가 이미 만석입니다.");
    }

}
