package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    private final Image validImage = new Image(500f, "png", "cdn.com", 600, 400);

    @Test
    @DisplayName("모집중 상태의 무료 강의는 수강 신청 가능하다")
    void freeSession_joinable_whenRecruiting() {
        Session session = new Session(
                "무료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,             // tuition
                0,             // currentCount
                0,             // capacity (무제한이지만 그냥 0으로 둠)
                validImage,
                SessionStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isTrue();
    }

    @Test
    @DisplayName("모집중이 아닌 무료 강의는 수강 신청이 불가능하다")
    void freeSession_notJoinable_whenNotRecruiting() {
        Session session = new Session(
                "무료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,
                0,
                0,
                validImage,
                SessionStatus.PREPARING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isFalse();
    }

    @Test
    @DisplayName("모집중이고 정원이 남았고 결제 금액이 일치하면 유료 강의 수강 신청 가능")
    void paidSession_joinable_whenRecruiting_underCapacity_andPaidCorrectly() {
        Session session = new Session(
                "유료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                10000L,   // tuition
                29,      // currentCount
                30,      // capacity
                validImage,
                SessionStatus.RECRUITING,
                new PaidJoinStrategy()
        );

        assertThat(session.joinable(new Payment(10000L))).isTrue();
    }

    @Test
    @DisplayName("모집중이어도 결제 금액이 일치하지 않으면 유료 강의 수강 신청 불가")
    void paidSession_notJoinable_whenWrongAmount() {
        Session session = new Session(
                "유료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                10000L,
                10,
                30,
                validImage,
                SessionStatus.RECRUITING,
                new PaidJoinStrategy()
        );

        assertThat(session.joinable(new Payment(8000L))).isFalse();
    }

    @Test
    @DisplayName("모집중이어도 정원이 초과되면 유료 강의 수강 신청 불가")
    void paidSession_notJoinable_whenOverCapacity() {
        Session session = new Session(
                "유료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                10000L,
                30,
                30,
                validImage,
                SessionStatus.RECRUITING,
                new PaidJoinStrategy()
        );

        assertThat(session.joinable(new Payment(10000L))).isFalse();
    }
}
