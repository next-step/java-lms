package nextstep.session;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nextstep.session.TestFixtures.endSession;
import static nextstep.session.TestFixtures.preparingSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionStatusTest {

    @ParameterizedTest
    @CsvSource(value = {"PREPARING", "RECRUITING", "END"})
    void 강의_상태는_준비중_모집중_종료_3가지_상태를_가진다(String status){
        List<SessionStatus> sessionStatuses = Arrays.stream(SessionStatus.values()).collect(Collectors.toList());

        assertThat(sessionStatuses).contains(SessionStatus.valueOf(status));
    }

    @ParameterizedTest
    @MethodSource(value = "nonRegistrableSessions")
    void 모집중인_상태일_때만_강의_수강신청_할_수_있다(Session session) {
        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("수강 신청 불가능한 상태입니다.");
    }

    static Stream<Session> nonRegistrableSessions() {
        return Stream.of(endSession(), preparingSession());
    }
}
