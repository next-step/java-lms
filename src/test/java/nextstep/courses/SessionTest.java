package nextstep.courses;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionFeeType;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    public static Session testSession = createSessionForTest(LocalDate.now(), LocalDate.now().plusDays(3L), 10);

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @Test
    public void sessionEnrolmentTest_모집중이_아닌_경우() {
        Session session = createSessionForTest(LocalDate.now(), LocalDate.now().plusDays(1L), 10);
        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("종료된 강의는 모집중으로 변경할 수 없다.")
    @Test
    public void sessionEnrolmentTest_종료된_강의를_모집중으로_변경하는_경우() {
        Session session = createSessionForTest(LocalDate.now().minusDays(3L), LocalDate.now().minusDays(1L), 10);
        assertThatThrownBy(session::startRecruit)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("강의 수강신청은 최대 수강 인원을 초과할 수 없다.")
    @Test
    public void sessionEnrolmentTest_최대수강인원을_초과한_경우() {
        Session session = createSessionForTest(LocalDate.now(), LocalDate.now().plusDays(1L), 1);
        session.startRecruit();
        session.register(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> session.register(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Session createSessionForTest(LocalDate startDate, LocalDate endDate, int maxCountOfStudent) {
        return new Session("TEST", startDate, endDate, SessionFeeType.FREE, maxCountOfStudent);
    }
}
