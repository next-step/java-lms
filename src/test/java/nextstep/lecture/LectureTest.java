package nextstep.lecture;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
- 무료 강의를 생성한다.
- 유료 강의를 생성한다.
- 강의를 생성할 시 시작일보다 종료일이 빠르면 예외가 발생한다.
- 강의를 모집중으로 변경한다.
- 강의를 종료한다.
- 무료 강의를 신청할 시 수강인원이 1 증가한다.
- 강의를 신청할 시 모집중이 아니면 예외가 발생한다.
- 유료 강의를 신청할 시 수강생이 결제한 금액과 수강료가 일치하지 않으면 예외가 발생한다.
- 유료 강의를 신청할 시 결제내역이 없으면 예외가 발생한다.
- 강의가 이미 만석이면 예외를 발생한다.
*/
public class LectureTest {

    @DisplayName("무료 강의를 생성한다.")
    @Test
    void createFreeLectureTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createFree(1L, "테스트강의", image, startDate, endDate);

        assertThat(lecture)
                .extracting("title", "image", "paymentType", "startDate", "endDate")
                .contains("테스트강의", PaymentType.FREE, image, startDate, endDate);
    }

    @DisplayName("유료 강의를 생성한다.")
    @Test
    void createPaidLectureTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);

        assertThat(lecture)
                .extracting("title", "image", "paymentType", "startDate", "endDate", "subscribeMax", "price")
                .contains("테스트강의", PaymentType.PAID, image, 100, 800000, startDate, endDate);
    }

    @DisplayName("강의를 생성할 시 시작일보다 종료일이 빠르면 예외가 발생한다.")
    @Test
    void createLectureNotExistStartDateThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-05-05");
        Date endDate = simpleDateFormat.parse("2023-04-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        assertThatThrownBy(() -> Lecture.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료일이 시작일보다 빠릅니다.");
    }

    @DisplayName("강의를 모집중으로 변경한다.")
    @Test
    void lectureWaitTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createFree(1L, "테스트강의", image, startDate, endDate);
        lecture.waitLecture();
        assertThat(lecture.getSubscribeStatus()).isEqualTo(SubscribeStatus.WAIT);
    }

    @DisplayName("강의를 종료한다.")
    @Test
    void lectureClosedTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createFree(1L, "테스트강의", image, startDate, endDate);
        lecture.closedLecture();
        assertThat(lecture.getSubscribeStatus()).isEqualTo(SubscribeStatus.CLOSED);
    }

    @DisplayName("무료 강의를 신청할 시 수강인원이 1 증가한다.")
    @Test
    void subscribeLectureAddSubscribeCountTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createFree(1L, "테스트강의", image, startDate, endDate);
        lecture.waitLecture();
        lecture.subsribe();
        assertThat(lecture.getSubscribeCount()).isEqualTo(1);
    }

    @DisplayName("강의를 신청할 시 모집중이 아니면 예외가 발생한다")
    @Test
    void subscribeLectureNotWaitThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);

        Payment payment = new Payment(1L, 1L, 1L, 700000);

        assertThatThrownBy(() -> lecture.subsribe(payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의가 모집중이 아닙니다.");
    }

    @DisplayName("유료 강의를 신청할 시 수강생이 결제한 금액과 수강료가 일치하지 않으면 예외가 발생한다.")
    @Test
    void subscribeLectureNotCorrectPaymentThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);

        Payment payment = new Payment(1L, 1L, 1L, 700000);

        lecture.waitLecture();

        assertThatThrownBy(() -> lecture.subsribe(payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @DisplayName("유료 강의를 신청할 시 결제내역이 없으면 예외가 발생한다.")
    @Test
    void subscribeLectureWithoutPaymentThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createPaid(1L, "테스트강의", image, 100, 800000, startDate, endDate);
        lecture.waitLecture();
        assertThatThrownBy(lecture::subsribe)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료강의는 결제내역이 필수입니다.");
    }

    @DisplayName("강의가 이미 만석이면 예외를 발생한다.")
    @Test
    void subscribeLectureAlreadyMaxThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        Lecture lecture = Lecture.createPaid(1L, "테스트강의", image, 1, 800000, startDate, endDate);
        Payment payment = new Payment(1L, 1L, 1L, 800000);

        lecture.waitLecture();
        lecture.subsribe(payment);

        assertThatThrownBy(() -> lecture.subsribe(payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의가 이미 만석입니다.");
    }

}
