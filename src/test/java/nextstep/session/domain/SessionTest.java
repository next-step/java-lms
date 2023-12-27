package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.session.domain.fixture.SessionImageFixture.sessionImageFixture;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    LocalDate today;
    Session freeSession;
    Session paidSession;

    @BeforeEach
    void setup() {
        today = LocalDate.now();

        freeSession = FreeSession.create(1L, today, today.plusDays(1), sessionImageFixture);

        paidSession = PaidSession.create(1L, today, today.plusDays(1), sessionImageFixture, 2, 1000L);
        STUDENT_1.addPayment(new Payment(1000L, paidSession, STUDENT_1));
    }

    @Test
    @DisplayName("수강신청 상태검증 / Open / 성공")
    void 모집상태Open_성공() {
        freeSession.changeRecruit(SessionRecruitStatus.OPEN);
        paidSession.changeRecruit(SessionRecruitStatus.OPEN);

        freeSession.enroll(STUDENT_1);
        paidSession.enroll(STUDENT_1);
    }

    @Test
    @DisplayName("수강신청 상태검증 / Closed / IllegalStatementException")
    void 모집상태Closed_실패() {
        assertThatThrownBy(() -> freeSession.enroll(STUDENT_1)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> paidSession.enroll(STUDENT_1)).isInstanceOf(IllegalStateException.class);
    }
}
