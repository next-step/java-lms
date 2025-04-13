package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private static final NsUser USER = new NsUser(1L, "user", "password", "name", "email");
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = START_DATE.plusMonths(1);

    @Test
    @DisplayName("강의를 생성한다")
    void create() {
        Session session = new Session(
            1L,
            "강의 제목",
            SessionStatus.RECRUITING,
            START_DATE,
            END_DATE,
            new SessionImage("image.jpg", 300, 200),
            SessionType.PAID,
            30,
            10000
        );

        assertThat(session.isPaid()).isTrue();
        assertThat(session.isRecruiting()).isTrue();
        assertThat(session.isFull()).isFalse();
    }

    @Test
    @DisplayName("무료 강의를 생성한다")
    void createFreeSession() {
        Session session = new Session(
            1L,
            "강의 제목",
            SessionStatus.RECRUITING,
            START_DATE,
            END_DATE,
            new SessionImage("image.jpg", 300, 200),
            SessionType.FREE,
            0,
            0
        );

        assertThat(session.isPaid()).isFalse();
        assertThat(session.isRecruiting()).isTrue();
        assertThat(session.isFull()).isFalse();
    }

    @Test
    @DisplayName("수강 신청을 한다")
    void enroll() {
        Session session = new Session(
            1L,
            "강의 제목",
            SessionStatus.RECRUITING,
            START_DATE,
            END_DATE,
            new SessionImage("image.jpg", 300, 200),
            SessionType.PAID,
            30,
            10000
        );
        Payment payment = new Payment("payment1", 1L, 1L, 10000L);

        assertThat(session.canEnroll(USER)).isTrue();
        session.enroll(USER, payment);
        assertThat(session.hasEnrolledUser(USER)).isTrue();
        assertThat(session.canEnroll(USER)).isFalse();
    }

    @Test
    @DisplayName("무료 강의에 수강 신청을 한다")
    void enrollFreeSession() {
        Session session = new Session(
            1L,
            "강의 제목",
            SessionStatus.RECRUITING,
            START_DATE,
            END_DATE,
            new SessionImage("image.jpg", 300, 200),
            SessionType.FREE,
            0,
            0
        );

        assertThat(session.canEnroll(USER)).isTrue();
        session.enroll(USER, null);
        assertThat(session.hasEnrolledUser(USER)).isTrue();
        assertThat(session.canEnroll(USER)).isFalse();
    }

    @Test
    @DisplayName("수강 인원이 가득 찬 강의는 수강 신청이 불가능하다")
    void enrollFullSession() {
        Session session = new Session(
            1L,
            "강의 제목",
            SessionStatus.RECRUITING,
            START_DATE,
            END_DATE,
            new SessionImage("image.jpg", 300, 200),
            SessionType.PAID,
            1,
            10000
        );
        Payment payment = new Payment("payment1", 1L, 1L, 10000L);
        NsUser anotherUser = new NsUser(2L, "user2", "password", "name", "email");

        session.enroll(USER, payment);
        assertThat(session.isFull()).isTrue();
        assertThat(session.canEnroll(anotherUser)).isFalse();
        assertThatThrownBy(() -> session.enroll(anotherUser, payment))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("최대 수강 인원을 초과했습니다.");
    }
} 