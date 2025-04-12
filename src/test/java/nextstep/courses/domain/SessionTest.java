package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private static final Long SESSION_ID = 1L;
    private static final String TITLE = "TDD와 Clean Code";
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = START_DATE.plusDays(30);
    private static final NsUser USER = new NsUser(1L, "user@email.com", "password", "name", "010-1234-5678");

    @Test
    @DisplayName("무료 강의를 생성한다")
    void createFreeSession() {
        // given
        Session session = new Session(SESSION_ID, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                null, SessionType.FREE, 0, 0);

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
        Session session = new Session(SESSION_ID, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                null, SessionType.PAID, 30, 100000);

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
        assertThatThrownBy(() -> new Session(SESSION_ID, TITLE, SessionStatus.RECRUITING, invalidStartDate, END_DATE,
                null, SessionType.FREE, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 이전이어야 합니다.");
    }

    @Test
    @DisplayName("무료 강의에 수강 신청한다")
    void enrollFreeSession() {
        // given
        Session session = new Session(SESSION_ID, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                null, SessionType.FREE, 0, 0);

        // when
        session.enroll(USER, null);

        // then
        assertThat(session.getCurrentEnrollment()).isEqualTo(1);
        assertThat(session.getEnrolledUsers()).hasSize(1);
        assertThat(session.getEnrolledUsers().get(0)).isEqualTo(USER);
    }

    @Test
    @DisplayName("유료 강의에 수강 신청한다")
    void enrollPaidSession() {
        // given
        Session session = new Session(SESSION_ID, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                null, SessionType.PAID, 30, 100000);
        Payment payment = new Payment("payment-1", SESSION_ID, USER.getId(), 100000L);

        // when
        session.enroll(USER, payment);

        // then
        assertThat(session.getCurrentEnrollment()).isEqualTo(1);
        assertThat(session.getEnrolledUsers()).hasSize(1);
        assertThat(session.getEnrolledUsers().get(0)).isEqualTo(USER);
    }

    @Test
    @DisplayName("모집중이 아닌 강의에 수강 신청하면 예외가 발생한다")
    void enrollNotRecruitingSession() {
        // given
        Session session = new Session(SESSION_ID, TITLE, SessionStatus.PREPARING, START_DATE, END_DATE,
                null, SessionType.FREE, 0, 0);

        // when & then
        assertThatThrownBy(() -> session.enroll(USER, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중인 강의만 수강 신청이 가능합니다.");
    }

    @Test
    @DisplayName("유료 강의에 결제 없이 수강 신청하면 예외가 발생한다")
    void enrollPaidSessionWithoutPayment() {
        // given
        Session session = new Session(SESSION_ID, TITLE, SessionStatus.RECRUITING, START_DATE, END_DATE,
                null, SessionType.PAID, 30, 100000);

        // when & then
        assertThatThrownBy(() -> session.enroll(USER, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 결제가 필요합니다.");
    }
} 