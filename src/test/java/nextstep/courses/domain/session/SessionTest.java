package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    public static Session testSession = createSessionForTest(LocalDate.now(), LocalDate.now().plusDays(3L), 10);

    @DisplayName("종료된 강의는 모집중으로 변경할 수 없다.")
    @Test
    public void sessionEnrolmentTest_종료된_강의를_모집중으로_변경하는_경우() {
        Session session = createSessionForTest(LocalDate.now().minusDays(3L), LocalDate.now().minusDays(1L), 10);
        assertThatThrownBy(session::startRecruit)
                .isInstanceOf(IllegalStateException.class);
    }

    private static Session createSessionForTest(LocalDate startDate, LocalDate endDate, int capacity) {
        return new Session("TEST", startDate, endDate, SessionFeeType.FREE, capacity);
    }
}
