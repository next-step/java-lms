package nextstep.qna.domain;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    @Test
    @DisplayName("모집중이 아닌 경우, 수강신청이 불가능하다")
    void notRegisterSession() {
        Session session = new FreeSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.CLOSED
        );
        assertThatThrownBy(() -> {
            session.register(1L, new Payment());
        }).isInstanceOf(IllegalStateException.class).hasMessage("수강 신청은 모집중일 때만 가능합니다.");
    }
    @Test
    @DisplayName("모집중인 경우, 수강신청이 가능하다")
    void registerSession() {
        Session session = new FreeSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.RECRUITING
        );
        session.register(1L, new Payment());
        assertThat(session.getRegisteredStudent().contains(new Student(1L))).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 수강 인원이 제한되어 있다.")
    void paidSession() {
        Session session = new PaidSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.RECRUITING,
                1,
                10000
        );
        assertThatThrownBy(() -> {
            session.register(11L, new Payment(
                    "1",
                    1L,
                    11L,
                    10000
            ));
            session.register(11L, new Payment(
                    "1",
                    1L,
                    11L,
                    10000
            ));
        }).isInstanceOf(IllegalStateException.class).hasMessage("수강 인원을 초과하였습니다.");
    }

    @Test
    @DisplayName("유료 강의는 결제 정보가 없다면 수강신청이 불가능하다.")
    void paidSessionWithoutPayment() {
        Session session = new PaidSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.RECRUITING,
                10,
                10000
        );
        assertThatThrownBy(() -> {
            session.register(11L, null);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("결제 정보가 없습니다.");
    }

    @Test
    @DisplayName("결제한 강의와 일치하지 않으면, 수강신청이 불가능하다.")
    void paidSessionWithDifferentPayment() {
        Session session = new PaidSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.RECRUITING,
                10,
                10000
        );
        assertThatThrownBy(() -> {
            session.register(11L, new Payment(
                    "1",
                    2L,
                    11L,
                    10000
            ));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("결제한 강의와 일치하지 않습니다.");
    }

    @Test
    @DisplayName("결제한 사용와 일치하지 않으면 수강신청이 불가능하다.")
    void paidSessionWithDifferentStudent() {
        Session session = new PaidSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.RECRUITING,
                10,
                10000
        );
        assertThatThrownBy(() -> {
            session.register(11L, new Payment(
                    "1",
                    1L,
                    12L,
                    10000
            ));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("결제한 사용자와 일치하지 않습니다.");
    }

    @Test
    @DisplayName("결제한 금액과 일치하지 않으면 수강신청이 불가능하다.")
    void paidSessionWithDifferentTuitionFee() {
        Session session = new PaidSession(
                1L,
                "자바지기",
                new Period(LocalDate.now(), LocalDate.now().plusDays(1)),
                null,
                SessionStatus.RECRUITING,
                10,
                10000
        );
        assertThatThrownBy(() -> {
            session.register(11L, new Payment(
                    "1",
                    1L,
                    11L,
                    20000
            ));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("결제 금액과 일치하지 않습니다.");
    }

}
