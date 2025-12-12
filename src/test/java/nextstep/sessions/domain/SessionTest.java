package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionTest {

    static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);

    @Test
    void startDateMustBeBeforeEndDate() {
        assertThatThrownBy(() -> new Session(END_DATE, START_DATE)).isInstanceOf(
                IllegalArgumentException.class).hasMessageContaining("시작일이 종료일보다");
    }

    @Test
    void sessionStatusIsPreparingOnCreation() {
        Session session = new Session(START_DATE, END_DATE);
        assertThat(session.status()).isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    void whenCreatingFreeSession_thenMaxCapacityIsNull() {
        Session freeSession = new Session(START_DATE, END_DATE, false, null);
        assertThat(freeSession.isPaid()).isFalse();
        assertThat(freeSession.maxCapacity()).isNull();
    }

    @Test
    void whenCreatingFreeSessionWithNonNullCapacity_thenThrow() {
        assertThatThrownBy(() -> new Session(START_DATE, END_DATE, false, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("무료 강의는 최대 수강인원이 없어야 합니다");
    }

    @Test
    void whenCreatingPaidSession_thenMaxCapacityMustBePositive() {
        Session paidSession = new Session(START_DATE, END_DATE, true, 5, 100_000);
        assertThat(paidSession.isPaid()).isTrue();
        assertThat(paidSession.maxCapacity()).isEqualTo(5);
    }

    @Test
    void whenCreatingPaidSessionWithInvalidCapacity_thenThrow() {
        assertThatThrownBy(() -> new Session(START_DATE, END_DATE, true, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");

        assertThatThrownBy(() -> new Session(START_DATE, END_DATE, true, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");
    }

    @Test
    void whenCreatingFreeSession_thenFeeIsZero() {
        Session freeSession = new Session(START_DATE, END_DATE, false, null, 0);
        assertThat(freeSession.fee()).isEqualTo(0);
    }

    @Test
    void whenCreatingFreeSessionWithInvalidFee_thenThrow() {
        assertThatThrownBy(() -> new Session(START_DATE, END_DATE, false, null, 500_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("무료 강의는 0원");
    }

    @Test
    void whenCreatingPaidSession_thenFeeIsOverZero() {
        Session paidSession = new Session(START_DATE, END_DATE, true, 5, 100_000);
        assertThat(paidSession.fee()).isEqualTo(100_000);
    }

    @Test
    void whenCreatingPaidSessionWithInvalidFee_thenThrow() {
        assertThatThrownBy(() -> new Session(START_DATE, END_DATE, true, 5, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 0원 초과");
    }
}
