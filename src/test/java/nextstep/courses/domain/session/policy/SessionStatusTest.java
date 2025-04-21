package nextstep.courses.domain.session.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionStatusTest {

    @DisplayName("준비중 일때 수강신청이 불가능하다")
    @Test
    public void testCanEnroll_Preparing() {
        assertThat(SessionStatus.PREPARING.canEnroll()).isFalse();
    }

    @DisplayName("모집중 일때 수강신청이 가능하다")
    @Test
    public void testCanEnroll_Enrolling() {
        assertThat(SessionStatus.ENROLLING.canEnroll()).isTrue();
    }


    @DisplayName("진행중 일때 수강신청이 가능하다")
    @Test
    public void testCanEnroll_OnGoing() {
        assertThat(SessionStatus.ONGOING.canEnroll()).isTrue();
    }

    @DisplayName("종료 일때 수강신청이 불가능하다")
    @Test
    public void testCanEnroll_Closed() {
        assertThat(SessionStatus.CLOSED.canEnroll()).isFalse();
    }
}
