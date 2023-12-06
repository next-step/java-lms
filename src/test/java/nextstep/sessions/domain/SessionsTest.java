package nextstep.sessions.domain;

import nextstep.payments.domain.Payments;
import nextstep.users.domain.NsUserTest;
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

    @DisplayName("강의 리스트 중 모집 종료된 강의가 있다면 IllegalStateException을 던진다.")
    @Test
    void sessionExceptionTest() {
        assertThatThrownBy(() -> new Sessions(List.of(SessionTest.JAVA_TDD_16)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("만료된 강의를 전달하면 IllegalStateException을 던진다.")
    @Test
    void addSessionExceptionTest() {
        Sessions sessions = new Sessions();
        assertThatThrownBy(() -> sessions.addSession(SessionTest.JAVA_TDD_16))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("모집 인원이 마감된 강의를 전달하면 IllegalStateException을 던진다.")
    @Test
    void addSessionExceptionTest2() {
        Sessions sessions = new Sessions();
        Session computer = new Session("COMPUTER", SessionDateTest.DEC, SessionImageTest.IMAGE_JPG, SessionChargeTest.CHARGE_100, SessionStudentTest.ONE, SessionStatus.RECRUITING);
        assertThatThrownBy(() -> sessions.addSession(computer))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("강의 결제를 하면 Payments를 반환한다.")
    @Test
    void payTest() {
        Sessions sessions = new Sessions(List.of(SessionTest.JAVA, SessionTest.JAVA_TDD_17));
        assertThat(sessions.pay(NsUserTest.JAVAJIGI.getId())).isInstanceOf(Payments.class);
    }
}
