package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private static final String TITLE = "TDD와 Clean Code";
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = START_DATE.plusDays(30);
    private static final SessionThumbnail THUMBNAIL = new SessionThumbnail("test.jpg", 500 * 1024, 300, 200);

    @Test
    @DisplayName("무료 강의를 생성한다")
    void createFreeSession() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.FREE, 0, 0);

        // when & then
        assertThat(session).isNotNull();
        assertThat(session.getType()).isEqualTo(SessionType.FREE);
        assertThat(session.getMaxEnrollment()).isEqualTo(0);
        assertThat(session.getPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("유료 강의를 생성한다")
    void createPaidSession() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 30, 100000);

        // when & then
        assertThat(session).isNotNull();
        assertThat(session.getType()).isEqualTo(SessionType.PAID);
        assertThat(session.getMaxEnrollment()).isEqualTo(30);
        assertThat(session.getPrice()).isEqualTo(100000);
    }

    @Test
    @DisplayName("시작일이 종료일보다 늦으면 예외가 발생한다")
    void validateDates() {
        // given
        LocalDateTime invalidStartDate = END_DATE.plusDays(1);

        // when & then
        assertThatThrownBy(() -> new Session(1L, TITLE, SessionStatus.RECRUITING, invalidStartDate, END_DATE,
                THUMBNAIL, SessionType.FREE, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 이전이어야 합니다.");
    }

    @Test
    @DisplayName("유료 강의의 최대 수강 인원이 0이면 예외가 발생한다")
    void validateMaxEnrollment() {
        // when & then
        assertThatThrownBy(() -> new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 0, 100000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("유료 강의의 수강료가 0이면 예외가 발생한다")
    void validatePrice() {
        // when & then
        assertThatThrownBy(() -> new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 30, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 수강료가 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("무료 강의에 수강 신청한다")
    void enrollFreeSession() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.FREE, 0, 0);

        // when
        session.enroll(null);

        // then
        assertThat(session.getCurrentEnrollment()).isEqualTo(1);
    }

    @Test
    @DisplayName("유료 강의에 수강 신청한다")
    void enrollPaidSession() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 30, 100000);
        Payment payment = new Payment("payment-1", 1L, 1L, 100000L);

        // when
        session.enroll(payment);

        // then
        assertThat(session.getCurrentEnrollment()).isEqualTo(1);
    }

    @Test
    @DisplayName("모집중이 아닌 강의에 수강 신청하면 예외가 발생한다")
    void enrollNotRecruitingSession() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.PREPARING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.FREE, 0, 0);

        // when & then
        assertThatThrownBy(() -> session.enroll(null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중인 강의만 수강 신청이 가능합니다.");
    }

    @Test
    @DisplayName("유료 강의에 결제 없이 수강 신청하면 예외가 발생한다")
    void enrollPaidSessionWithoutPayment() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 30, 100000);

        // when & then
        assertThatThrownBy(() -> session.enroll(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 결제가 필요합니다.");
    }

    @Test
    @DisplayName("유료 강의에 결제 금액이 수강료와 다르면 예외가 발생한다")
    void enrollPaidSessionWithInvalidPayment() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 30, 100000);
        Payment payment = new Payment("payment-1", 1L, 1L, 90000L);

        // when & then
        assertThatThrownBy(() -> session.enroll(payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 수강료와 일치하지 않습니다.");
    }

    @Test
    @DisplayName("유료 강의의 최대 수강 인원을 초과하면 예외가 발생한다")
    void enrollExceedMaxEnrollment() {
        // given
        Session session = new Session(1L, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                THUMBNAIL, SessionType.PAID, 1, 100000);
        Payment payment = new Payment("payment-1", 1L, 1L, 100000L);

        // when
        session.enroll(payment);

        // then
        assertThatThrownBy(() -> session.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강 인원을 초과했습니다.");
    }
} 