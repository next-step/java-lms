package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class SessionTest {
    @DisplayName("무료강의 생성 테스트")
    @Test
    void create_free_session() throws Exception {
        Session.freeOf("test_session", 2L,
                0.5, 300, 200, "jpg",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                SessionStatus.ENROLLING
        );
    }

    @DisplayName("성공 - 수강신청 테스트")
    @Test
    void enroll_session() throws Exception {
        Session paidSession = Session.paidOf("test_session", 2L,
                0.5, 300, 200, "jpg",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                SessionStatus.ENROLLING, 2,
                800_000
        );
        Payment payment = new Payment("payment_id_1", 2L, 2L, 800_000L);
        paidSession.enroll(UserTest.JAVAJIGI, payment);
        assertThat(paidSession.countEnrolledStudent()).isEqualTo(1);
    }

    @DisplayName("실패 - 결제 금액 불일치 테스트")
    @Test
    void enroll_session_failure_amountMismatch() throws Exception {
        // 유료 세션 생성
        Session paidSession = Session.paidOf("test_session", 2L,
                0.5, 300, 200, "jpg",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                SessionStatus.ENROLLING, 2,
                800_000
        );
        Payment payment = new Payment("payment_id_2", 2L, 2L, 600_000L);
        assertThatIllegalArgumentException().isThrownBy(() -> paidSession.enroll(UserTest.JAVAJIGI, payment));
    }
}