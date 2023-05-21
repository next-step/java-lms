package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


class SessionTest {
    @ParameterizedTest(name = "[{index}/2] {displayName}")
    @EnumSource(value = SessionStatus.class, names = {"READY", "END"})
    @DisplayName("강의 상태가 모집중이 아닐 경우, IllegalArgumentException 예외 발생")
    void apply_session_when_session_status_is_not_RECRUIT_then_throw_IllegalArgumentException(SessionStatus status) {
        // given
        Session session = new Session("nextstep_tdd", status);

        // when, then
        Assertions.assertThatThrownBy(() -> session.apply(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의는 모집중일 때 신청 가능합니다: " + status);
    }
}