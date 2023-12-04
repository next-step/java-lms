package nextstep.session;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionStatus;
import org.junit.jupiter.api.Test;
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

class SessionTest {

    @ParameterizedTest
    @CsvSource(value = {"PREPARING", "RECRUITING", "END"})
    void 강의_상태는_준비중_모집중_종료_3가지_상태를_가진다(String status){
        List<SessionStatus> sessionStatuses = Arrays.stream(SessionStatus.values()).collect(Collectors.toList());

        assertThat(sessionStatuses).contains(SessionStatus.valueOf(status));
    }

    @ParameterizedTest
    @MethodSource(value = "nonRegistrableSessions")
    void 강의는_모집중인_상태일_때만_수강신청_할_수_있다(Session session) {
        assertThatThrownBy(session::register)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("수강신청은 모집중인 상태일 때만 가능합니다.");
    }

    static Stream<Session> nonRegistrableSessions() {
        return Stream.of(endSession(), preparingSession());
    }
}
