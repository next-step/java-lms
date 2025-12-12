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
        assertThatThrownBy(() -> new Session(END_DATE, START_DATE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일이 종료일보다");
    }

    @Test
    void sessionStatusIsPreparingOnCreation() {
        Session session = new SessionTestBuilder().free().build();
        assertThat(session.status()).isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    void whenCreatingFreeSession_thenMaxCapacityIsNull() {
        Session freeSession = new SessionTestBuilder().free().build();
        assertThat(freeSession.isPaid()).isFalse();
        assertThat(freeSession.maxCapacity()).isNull();
    }

    @Test
    void whenCreatingFreeSessionWithNonNullCapacity_thenThrow() {
        assertThatThrownBy(() -> new SessionTestBuilder().free().maxCapacity(10).build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("무료 강의는 최대 수강인원이 없어야 합니다");
    }

    @Test
    void whenCreatingPaidSession_thenMaxCapacityMustBePositive() {
        Session paidSession = new SessionTestBuilder().paid(5, 100_000).build();
        assertThat(paidSession.isPaid()).isTrue();
        assertThat(paidSession.maxCapacity()).isEqualTo(5);
    }

    @Test
    void whenCreatingPaidSessionWithInvalidCapacity_thenThrow() {
        assertThatThrownBy(() -> new SessionTestBuilder().paid(null, 100_000).build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");

        assertThatThrownBy(() -> new SessionTestBuilder().paid(0, 100_000).build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");
    }

    @Test
    void whenCreatingFreeSession_thenFeeIsZero() {
        Session freeSession = new SessionTestBuilder().free().build();
        assertThat(freeSession.fee()).isEqualTo(0);
    }

    @Test
    void whenCreatingFreeSessionWithInvalidFee_thenThrow() {
        assertThatThrownBy(() -> new SessionTestBuilder().free().fee(500_000).build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("무료 강의는 0원");
    }

    @Test
    void whenCreatingPaidSession_thenFeeIsOverZero() {
        Session paidSession = new SessionTestBuilder().paid(5, 100_000).build();
        assertThat(paidSession.fee()).isEqualTo(100_000);
    }

    @Test
    void whenCreatingPaidSessionWithInvalidFee_thenThrow() {
        assertThatThrownBy(() -> new SessionTestBuilder().paid(5, 0).build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 0원 초과");
    }

    @Test
    void whenSessionStatusIsOpen_thenCanEnrollIsTrue() {
        Session session = new SessionTestBuilder().free().build();
        session.startRecruiting();
        assertThat(session.canEnroll()).isTrue();
    }

    @Test
    void whenSessionStatusIsNotOpen_thenCanEnrollIsFalse() {
        Session session = new SessionTestBuilder().free().build();
        assertThat(session.canEnroll()).isFalse();
    }

    @Test
    void whenEnrollCountIsOverMaxCapacity_thenCanEnrollIsFalse() {
        Session session = new SessionTestBuilder()
                .paid(1, 100_000)
                .enrollCount(1)
                .build();
        assertThat(session.canEnroll()).isFalse();
    }

    @Test
    void whenEnroll_thenEnrollCountIncrease() {
        Session session = new SessionTestBuilder().paid(1, 100_000).build();
        session.startRecruiting();
        int before = session.enrollCount();
        session.enroll();
        int after = session.enrollCount();
        assertThat(after - before).isEqualTo(1);
    }

    @Test
    void whenEnrollImpossible_thenThrow() {
        Session session = new SessionTestBuilder()
                .paid(1, 100_000)
                .enrollCount(1)
                .build();
        assertThatThrownBy(session::enroll)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강 신청을 할 수 없습니다");
    }
}
