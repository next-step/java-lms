package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @DisplayName("수강 신청은 강의 상태가 모집 중일 때만 가능하다.")
    @ParameterizedTest
    @MethodSource("checkRegister")
    void test01(Session session, int amount) {
        session.changeStatus(SessionStatus.RECRUITING);
        NsUserTest.JAVAJIGI.pay(session.getId(), amount);
        assertThatCode(() -> {
            session.register(NsUserTest.JAVAJIGI);
        }).doesNotThrowAnyException();
    }

    @DisplayName("전체 수강생을 확인한다.")
    @ParameterizedTest
    @MethodSource("checkRegister")
    void test02(Session session, int amount) {
        session.changeStatus(SessionStatus.RECRUITING);

        session.registers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        assertThat(session.totalStudents()).containsExactlyInAnyOrder(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
    }

    private static Stream<Arguments> checkRegister() {
        return Stream.of(
                Arguments.of(new FreeSession(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1)), 0),
                Arguments.of(new PaidSession(2, 1, 10_000, LocalDateTime.now(), LocalDateTime.now().plusDays(1)), 10_000)
        );
    }

    @Nested
    class FreeSessionTest {

        @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
        @Test
        void test03() {
            FreeSession session = new FreeSession(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
            session.changeStatus(SessionStatus.RECRUITING);
            assertThatCode(() -> {
                for (int i = 0; i < 10_000; i++) {
                    NsUser user = new NsUser((long) i, String.valueOf(i), "password", "name", "test@email.com");
                    session.register(user);
                }
            }).doesNotThrowAnyException();
        }
    }

    @Nested
    class PaidSessionTest {

        @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
        @Test
        void test04() {
            Session session = new PaidSession(1, 1, 10000, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
            session.changeStatus(SessionStatus.RECRUITING);
            assertThatThrownBy(() -> session.registers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최대 수강인원을 초과하였습니다.");
        }

        @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치하지 않는 경우 예외가 발생한다.")
        @Test
        void test05() {
            NsUserTest.JAVAJIGI.pay(1, 10_000);
            Session session = new PaidSession(1, 1, 9000, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
            session.changeStatus(SessionStatus.RECRUITING);
            assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("결제 금액과 수강료가 일치하지 않습니다.");
        }
    }
}
