package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private static final Image IMAGE = new Image(1000, "jpeg", Image.WIDTH_MIN, Image.HEIGHT_MIN);
    private static final Payment PAYMENT = new Payment("1", 1L, 1L, 1000L);
    private static final LocalDate localDate = LocalDate.of(2023, 12, 5);
    private static final LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);

    @Test
    @DisplayName("강의는 이미지가 없으면 이미지를 추가하라는 예외를 반환한다.")
    void newObject_imageNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(null, localDate, localDate, Session.Type.FREE, 1000L, 10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("applySession은 수강신청을 하면 수강 신청 인원이 1 증가한다.")
    void applySession_success() {
        Session session = new Session(1L, IMAGE, localDate, localDate, Session.Type.FREE,
                1000L, 10, 1, Session.Status.RECRUIT, localDateTime, localDateTime);

        session.applySession(PAYMENT);

        assertThat(session.applyCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("applySession은 모집 중이 아니면 신청할 수 없다는 예외를 반환한다.")
    void applySession_notRecruitStatus_throwsException() {
        Session session = new Session(1L, IMAGE, localDate, localDate, Session.Type.FREE,
                1000L, 10, 0, Session.Status.READY, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.applySession(PAYMENT)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("applySession은 유료 강의 수강 인원 정원을 초과하면 신청할 수 없다는 예외를 반환한다..")
    void applySession_chargeSession_overQuota_throwsException() {
        Session session = new Session(1L, IMAGE, localDate, localDate, Session.Type.FREE,
                1000L, 10, 9, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.applySession(PAYMENT)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("applySession은 유료 강의 결제가 안되었다면 신청할 수 없다는 예외를 반환한다..")
    void applySession_chargeSession_notPaid_throwsException() {
        Session session = new Session(1L, IMAGE, localDate, localDate, Session.Type.FREE,
                1000L, 10, 9, Session.Status.RECRUIT, localDateTime, localDateTime);

        assertThatThrownBy(
                () -> session.applySession(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
