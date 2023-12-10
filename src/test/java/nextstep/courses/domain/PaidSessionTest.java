package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.PaidSessionException;
import nextstep.courses.exception.ParticipantsException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    private PaidSession paidSession;

    @BeforeEach
    void create() {
        LocalDateTime now = LocalDateTime.now();
        paidSession = new PaidSession(
                new CoverImage("images/test.gif", 1000_000, "gif", 300, 200, now),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.RECRUITING,
                800_000L,
                1,
                now
        );
    }

    @Test
    @DisplayName("수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void 수강신청_결제금액_에러() {
        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(1L, 1L, NsUserTest.SANJIGI, 700_000L)))
                .isInstanceOf(PaidSessionException.class);
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void 수강신청_인원Max_에러() {
        paidSession.apply(Payment.ofPaid(1L, 1L, NsUserTest.JAVAJIGI, 800_000L));

        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 800_000L)))
                .isInstanceOf(ParticipantsException.class);
    }

    @Test
    @DisplayName("중복 수강신청은 불가능하다.")
    void 수강신청_동일신청_에러() {
        paidSession.apply(Payment.ofPaid(1L, 1L, NsUserTest.JAVAJIGI, 800_000L));

        assertThatThrownBy(() -> paidSession.apply(Payment.ofPaid(2L, 1L, NsUserTest.JAVAJIGI, 800_000L)))
                .isInstanceOf(ParticipantsException.class);
    }
}
