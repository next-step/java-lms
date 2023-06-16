package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionRegistrationTest {

    @DisplayName("모집중일시 수강신청 테스트")
    @Test
    void openStatus() {
        Session session = testSession1();

        session.register(NsUserTest.JAVAJIGI);

        assertThat(session.getSessionJoins()).hasSize(1).extracting("session.id", "nsUser.id")
                .containsExactly(tuple(1L, 1L));
    }

    @DisplayName("준비중일 때 수강신청 시 오류")
    @Test
    void readyStatus() {
        Session session = testSession2();

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("종료일 때 수강신청 시 오류")
    @Test
    void closedStatus() {
        Session session = testSession3();

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의 최대 수강인원 달성 후 신청 시 오류")
    @Test
    void maximumUser() {
        Session session = testSession5();
        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복ID 신청 시 오류")
    @Test
    void duplicatedUser() {
        Session session = testSession1();
        session.register(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
