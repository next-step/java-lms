package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void sessionStatusIsPreparingOnCreation() {
        Session session = new SessionTestBuilder().free().build();
        assertThat(session.status()).isEqualTo(SessionStatus.PREPARING);
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
