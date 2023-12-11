package nextstep.sessions.domain;

import nextstep.common.PeriodTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SessionsTest {

    @DisplayName("강의 리스트를 전달하면 Sessions 객체를 생성한다.")
    @Test
    void sessionsTest() {
        assertThat(new Sessions(List.of(SessionTest.JAVA, SessionTest.JAVA_TDD_17))).isInstanceOf(Sessions.class);
    }

    @DisplayName("만료된 강의를 전달하면 IllegalStateException을 던진다.")
    @Test
    void addSessionExceptionTest() {
        Sessions sessions = new Sessions();
        assertThatThrownBy(() -> sessions.addSession(SessionTest.JAVA_TDD_16))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("모집 인원이 마감되지 않은 강의는 학생을 추가할 수 있다.")
    @Test
    void addStudentTest() {
        Sessions sessions = new Sessions(List.of(new Session("강의1",
                PeriodTest.DEC,
                SessionImageTest.IMAGE_PNG,
                new SessionCharge(true, 1000, 3),
                SessionStatus.RECRUITING)));
        sessions.addStudent();

        assertThat(sessions.getSessions().get(0).getStudentCount()).isEqualTo(1);
    }
}
